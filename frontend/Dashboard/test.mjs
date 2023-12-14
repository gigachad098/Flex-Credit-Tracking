import 'child_process';
import { ChildProcess } from 'child_process';

ChildProcess.


exec('echo test', (error, stdout, stderr) => {
    if (error) {
        console.error(`Error executing command: ${error}`);
        return;
    }

    console.log(`Command output: ${stdout}`);
});