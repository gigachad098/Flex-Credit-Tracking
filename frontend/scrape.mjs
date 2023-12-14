import puppeteer from "puppeteer";
import { format, subMonths } from 'date-fns';

if (process.argv.length != 3) {
  console.log("ERROR:\tNo temp password recieved")
  process.exit()
}

const browser = await puppeteer.launch({headless: "new"});
const page = await browser.newPage();
const todayDate = new Date();
const endDate = format(todayDate, 'yyyy-MM-dd');
const startDate = format(subMonths(todayDate, 18), 'yyyy-MM-dd');
enterPage();
async function enterPage() {
    const client = await page.target().createCDPSession();
    await client.send('Page.setDownloadBehavior', {
      behavior: 'allow',
      downloadPath: process.cwd()
    });
    await page.goto('https://atriumconnect.atriumcampus.com/login.php?cid=134&wason=/statementdetail.php&cid=134');
    await page.type('#loginphrase', 'flexcredittracker@gmail.com');
    await page.type('#password', process.argv[2]);
    await page.click('.icon-arrow-right');
    await page.waitForNavigation();
    //console.log(statementLink)
    const url = page.url()
    const beginning = url.split('skey=')[1]
    const ending = beginning.split('&cid=')[0]
    const key = ending
    const statementLink = `https://atriumconnect.atriumcampus.com/statementdetail.php?cid=134&skey=${key}&startdate=${startDate}&enddate=${endDate}&acct=71`
    await page.goto(statementLink);
    //console.log("Made it here!");
    const signIn = await page.waitForXPath("//a[contains(., 'CSV')]");
    //console.log("Wrapping it up!")
    signIn.evaluate(el => el.click())
    await new Promise(resolve => setTimeout(resolve, 1000));
    process.exit()
}