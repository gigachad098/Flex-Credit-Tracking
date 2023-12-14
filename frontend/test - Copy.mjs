import { exec } from 'node:child_process';

const command = "java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath \"..\\backend\\java;..\\backend\\java\\MongoDB\\src;..\\backend\\java\\MongoDB\\.m2\\repository\\org\\mongodb\\mongodb-driver-sync\\4.10.2\\mongodb-driver-sync-4.10.2.jar;..\\backend\\java\\MongoDB\\.m2\\repository\\org\\mongodb\\bson\\4.10.2\\bson-4.10.2.jar;..\\backend\\java\\MongoDB\\.m2\\repository\\org\\mongodb\\mongodb-driver-core\\4.10.2\\mongodb-driver-core-4.10.2.jar;..\\backend\\java\\MongoDB\\.m2\\repository\\org\\mongodb\\bson-record-codec\\4.10.2\\bson-record-codec-4.10.2.jar;..\\backend\\java\\MongoDB\\.m2\\repository\\ch\\qos\\logback\\logback-classic\\1.2.11\\logback-classic-1.2.11.jar;..\\backend\\java\\MongoDB\\.m2\\repository\\ch\\qos\\logback\\logback-core\\1.2.11\\logback-core-1.2.11.jar;..\\backend\\java\\MongoDB\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.7.32\\slf4j-api-1.7.32.jar\" Access 128";

exec(command, (error, stdout, stderr) => {
    if (error) {
        console.error(`Error executing command: ${error}`);
        return;
    }
    const string_output = `${stdout}`;
    const lines = string_output.split(/\r?\n/);
    var start = 0;
    for (let i = 0; i < lines.length; i++) {
        if (start == 1) {
            console.log(lines[i])
        }
        if (lines[i].localeCompare("***START OF ANALYZED DATA***") == 0) {
            start = 1;
        }
    }
});