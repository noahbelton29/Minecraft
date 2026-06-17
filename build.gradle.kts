plugins {
	java
	application
}

application {
	mainClass = "com.noahbelton29.minecraft.Main"
}

val lwjglVersion = "3.4.1"
val lwjglNatives = Pair(
	System.getProperty("os.name")!!,
	System.getProperty("os.arch")!!
).let { (name, arch) ->
	when {
		arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } ->
			if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
				"natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
			else "natives-linux"
		arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) } ->
			"natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"
		arrayOf("Windows").any { name.startsWith(it) } ->
			if (arch.contains("64")) "natives-windows" else "natives-windows-x86"
		else -> throw Error("Unsupported platform")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

	implementation("org.lwjgl:lwjgl")
	implementation("org.lwjgl:lwjgl-glfw")
	implementation("org.lwjgl:lwjgl-opengl")
	implementation("org.lwjgl:lwjgl-stb")

	runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
	runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
	runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
	runtimeOnly("org.lwjgl:lwjgl-stb::$lwjglNatives")

	implementation("org.joml:joml:1.10.7")
}

java {
	sourceCompatibility = JavaVersion.VERSION_26
	targetCompatibility = JavaVersion.VERSION_26
}