import puppeteer from "puppeteer";
import { compareAsc, format, subMonths } from 'date-fns';
const browser = await puppeteer.launch({headless: "new"});
const page = await browser.newPage();
const todayDate = new Date();
const endDate = format(todayDate, 'yyyy-MM-dd');
const startDate = format(subMonths(todayDate, 18), 'yyyy-MM-dd');
const statementLink = `https://atriumconnect.atriumcampus.com/statementdetail.php?cid=134&startdate=${startDate}&enddate=${endDate}&acct=71`;
enterPage();
async function enterPage() {
    const client = await page.target().createCDPSession();
    await client.send('Page.setDownloadBehavior', {
      behavior: 'allow',
      downloadPath: "C:\\Users\\tommy\\OneDrive\\Desktop\\Flex Tracker Prototype\\Flex-Credit-Tracking\\test-app"
    });
    await page.goto('https://atriumconnect.atriumcampus.com/login.php?cid=134&wason=/statementnew.php&cid=134');
    await page.type('#loginphrase', 'xanderlockard14@gmail.com');
    await page.type('#password', '769YD23N48');
    await page.click('.icon-arrow-right');
    await page.waitForNavigation();
    //console.log(statementLink)
    await page.goto(statementLink);
    //console.log("Made it here!");
    const signIn = await page.waitForXPath("//a[contains(., 'CSV')]");
    //console.log("Wrapping it up!")
    signIn.evaluate(el => el.click())
    await new Promise(resolve => setTimeout(resolve, 1000));
    process.exit()
}