package kr.nyaneo.gradlewrapper;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GradleWrapperManager extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String menu = Messages.showInputDialog("Minecraft to 1; Default to 2;", "Gradle Setting Configure", Messages.getQuestionIcon());
        String staticA = "";
        if (Objects.equals(menu, "1")) staticA = "You selected gradle option which supports for minecraft paper 1.20.1 api with Kommand, InvFx, mongo-driver and kotlin 1.9.10."; else if (Objects.equals(menu, "2")) staticA = "You just selected option which supports Default"; else staticA = "This is wrong Option. try again";
        Messages.showMessageDialog(menu, "Input: " + staticA, null);
        if (Objects.equals(menu, "1")) {
            try {
                Project project = e.getProject();
                File file = new File(project.getBasePath(), "build.gradle.kts");
                file.delete();
                file.createNewFile();

                String text = "import org.jetbrains.kotlin.gradle.tasks.KotlinCompile\n" +
                        "\n" +
                        "\n" +
                        "val authorName = \"nickname\"\n" +
                        "val pluginName = \"plugin\"\n" +
                        "val coreName = \"PluginCore\"\n" +
                        "val pluginVersion = \"1.0.0\"\n" +
                        "\n" +
                        "plugins {\n" +
                        "    kotlin(\"jvm\") version \"1.9.10\"\n" +
                        "    kotlin(\"plugin.lombok\") version \"1.9.10\"\n" +
                        "    id(\"io.freefair.lombok\") version \"8.1.0\"\n" +
                        "    application\n" +
                        "}\n" +
                        "\n" +
                        "group = \"io.github.${authorName}.${pluginName}\"\n" +
                        "version = \"1.0-SNAPSHOT\"\n" +
                        "\n" +
                        "repositories {\n" +
                        "    mavenCentral()\n" +
                        "    maven(\"https://repo.papermc.io/repository/maven-public/\")\n" +
                        "    maven(\"https://jitpack.io\")\n" +
                        "}\n" +
                        "\n" +
                        "dependencies {\n" +
                        "    testImplementation(kotlin(\"test\"))\n" +
                        "    implementation(kotlin(\"stdlib\"))\n" +
                        "    implementation(kotlin(\"reflect\"))\n" +
                        "    compileOnly(\"io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT\")\n" +
                        "    implementation(\"io.github.monun:kommand-api:3.1.6\")\n" +
                        "    implementation(\"io.github.monun:invfx-api:3.3.0\")\n" +
                        "    implementation(\"org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1\")\n" +
                        "    implementation(\"org.mongodb:mongodb-driver-sync:4.9.0\")\n" +
                        "\n" +
                        "}\n" +
                        "\n" +
                        "java {\n" +
                        "    toolchain {\n" +
                        "        languageVersion.set(JavaLanguageVersion.of(17))\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "tasks {\n" +
                        "    processResources {\n" +
                        "        filesMatching(\"plugin.yml\") {\n" +
                        "            expand(project.properties)\n" +
                        "            expand(extra.properties)\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    withType<KotlinCompile> {\n" +
                        "        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()\n" +
                        "    }\n" +
                        "\n" +
                        "    test {\n" +
                        "        useJUnitPlatform()\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    fun createPluginYml() {\n" +
                        "        val pluginFile = rootProject.file(\"src/main/resources/plugin.yml\")\n" +
                        "        if (!pluginFile.exists()) {\n" +
                        "            pluginFile.createNewFile()\n" +
                        "        }\n" +
                        "        pluginFile.writeText(\"\"\"\n" +
                        "            |name: ${pluginName}\n" +
                        "            |version: ${pluginVersion}\n" +
                        "            |main: io.github.${authorName}.${pluginName}.${coreName}\n" +
                        "            |api-version: 1.20\n" +
                        "            |libraries:\n" +
                        "            |    - io.github.monun:kommand-core:3.1.6\n" +
                        "            |    - io.github.monun:invfx-core:3.3.0\n" +
                        "            |    - org.mongodb:mongodb-driver-sync:4.3.3\n" +
                        "        \"\"\".trimMargin())\n" +
                        "    }\n" +
                        "\n" +
                        "    createPluginYml()\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "application {\n" +
                        "    mainClass.set(\"Main\")\n" +
                        "}";

                FileUtils.writeStringToFile(file, text, StandardCharsets.UTF_8);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (Objects.equals(menu, "2")) {
            try {
                Project project = e.getProject();
                File file = new File(project.getBasePath(), "build.gradle.kts");
                file.delete();
                file.createNewFile();
                String text = ("plugins {\n" +
                        "    kotlin(\"jvm\") version \"1.8.0\"\n" +
                        "}\n" +
                        "\n" +
                        "group = \"org.example\"\n" +
                        "version = \"1.0-SNAPSHOT\"\n" +
                        "\n" +
                        "repositories {\n" +
                        "    mavenCentral()\n" +
                        "}\n" +
                        "\n" +
                        "dependencies {\n" +
                        "    testImplementation(kotlin(\"test\"))\n" +
                        "}\n" +
                        "\n" +
                        "tasks.test {\n" +
                        "    useJUnitPlatform()\n" +
                        "}\n" +
                        "\n" +
                        "kotlin {\n" +
                        "    jvmToolchain(8)\n" +
                        "}");

                FileUtils.writeStringToFile(file, text, StandardCharsets.UTF_8);


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
