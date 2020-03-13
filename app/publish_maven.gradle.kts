buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Vers.Plugins.gradle}")
        classpath(kotlin("gradle-plugin", version = Vers.Plugins.kotlin))
    }
}

apply(plugin = "signing")
apply(plugin = "maven")

val repositoryUrl = "http://127.0.0.1:8091/repository/maven-releases/"
val repositoryUsername = "admin"
val repositoryPassword = "admin123"

afterEvaluate {
    // 如果要上传到maven的库是application类型的，需要修改下面的类型，第41行也需要修改
    val library = extensions.getByType(com.android.build.gradle.LibraryExtension::class)
    task<Javadoc>("androidJavadoc") {
        source = library.sourceSets.getByName("main").java.sourceFiles
        classpath += files(library.bootClasspath)
        classpath += files(configurations.getByName("compile").toList())
        include("**/*.java", "**/*.kt")
    }

    task<Jar>("androidJavadocJar") {
        dependsOn("androidJavadoc")
        classifier = "javadoc"
        from(tasks.getByName("androidJavadoc", Javadoc::class).destinationDir)
    }

    task<Jar>("androidSourcesJar") {
        classifier = "sources"
        from(library.sourceSets.getByName("main").java.srcDirs)
        include("**/*.java", "**/*.kt")
    }

    for (variant in library.libraryVariants) {
        val capName = variant.name.capitalize()
        task<Jar>("jar$capName") {
            dependsOn(variant.javaCompileProvider.get())
            from(variant.javaCompileProvider.get().destinationDir)
        }
    }

    artifacts {
        add("archives", tasks.getByName("androidSourcesJar"))
    }

    version = property("VERSION_NAME").toString()
    group = property("GROUP").toString()
    this@afterEvaluate.extensions.configure(SigningExtension::class) {
        setRequired {
            gradle.taskGraph.hasTask("uploadArchives")
        }
        sign(configurations.getByName("archives"))
    }

    tasks.getByName<Upload>("uploadArchives") {
        configuration = configurations.getByName("archives")
        repositories {
            withConvention(MavenRepositoryHandlerConvention::class) {
                mavenDeployer {
                    beforeDeployment {
                        val e =
                            this@afterEvaluate.extensions as org.gradle.api.internal.plugins.DefaultConvention
                        e.getByType(SigningExtension::class).signPom(this)
                    }
                    withGroovyBuilder {
                        "repository"("url" to repositoryUrl) {
                            "authentication"(
                                "userName" to repositoryUsername,
                                "password" to repositoryPassword
                            )
                        }
                        "pom" {
                            setProperty(
                                "artifactId",
                                this@afterEvaluate.property("POM_ARTIFACT_ID").toString()
                            )
                            setProperty("packaging", "aar")
                        }
                    }
                }
            }
        }
    }

    task<Upload>("installArchives") {
        configuration = configurations.getByName("archives")
        repositories {
            withConvention(MavenRepositoryHandlerConvention::class) {
                mavenDeployer {
                    withGroovyBuilder {
                        "repository"("url" to "file://${projectDir}/../../../../android")
                        "pom" {
                            setProperty(
                                "artifactId",
                                this@afterEvaluate.property("POM_ARTIFACT_ID").toString()
                            )
                            setProperty("packaging", "aar")
                        }
                    }
                }
            }
        }
    }
}