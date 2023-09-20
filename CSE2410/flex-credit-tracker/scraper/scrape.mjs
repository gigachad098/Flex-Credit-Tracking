import puppeteer from "puppeteer";
import { compareAsc, format, subMonths } from 'date-fns';
const browser = await puppeteer.launch({headless: "new"});
const page = await browser.newPage();
const todayDate = new Date();
const endDate = format(todayDate, 'yyyy-MM-dd');
const startDate = format(subMonths(todayDate, 18), 'yyyy-MM-dd');
const statementLink = `https://atriumconnect.atriumcampus.com/.php?cid=134&startdate=${startDate}&enddate=${endDate}&acct=71`;
enterPage();
async function enterPage() {
    const client = await page.target().createCDPSession();
    await client.send('Page.setDownloadBehavior', {
      behavior: 'allow',
      downloadPath: './'
    });
    await page.goto('https://atriumconnect.atriumcampus.com/login.php?cid=134&wason=/statementnew.php&cid=134');
    await page.type('#loginphrase', 'xanderlockard14@gmail.com');
    await page.type('#password', '769YD23N48');
    await page.click('.icon-arrow-right');
    await page.waitForNavigation();
    await page.goto(statementLink)
    const signIn = await page.waitForXPath("//a[contains(., 'CSV')]");
    await Promise.all([
      signIn.evaluate(el => el.click()),
      page.waitForNavigation()
    ]);
}