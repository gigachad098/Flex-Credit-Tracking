import puppeteer from "puppeteer";
import { compareAsc, format, subMonths } from 'date-fns';
// const browser = await puppeteer.launch({headless: "new"});
const browser = await puppeteer.launch({headless: false});
const page = await browser.newPage();
const todayDate = new Date();
const endDate = format(todayDate, 'yyyy-MM-dd');
const startDate = format(subMonths(todayDate, 18), 'yyyy-MM-dd');
enterPage();
async function enterPage() {
    const client = await page.target().createCDPSession();
    await client.send('Page.setDownloadBehavior', {
      behavior: 'allow',
      downloadPath: './'
    });
    await page.goto('https://services.jsatech.com/login.php?cid=134&wason=/statementnew.php&cid=134');
    await page.type('#loginphrase', 'flexcredittracker@gmail.com');
    await page.type('#password', '837TRZ89NG');
    await page.click('.icon-arrow-right');
    await page.waitForNavigation();
    const url = page.url()
    const beginning = url.split('skey=')[1]
    const ending = beginning.split('&cid=')[0]
    const key = ending
    const statementLink = `https://atriumconnect.atriumcampus.com/statementdetail.php?cid=134&skey=${key}&startdate=${startDate}&enddate=${endDate}&acct=71`
    await page.goto(statementLink)
    const signIn = await page.waitForXPath("//a[contains(., 'CSV')]");
    await Promise.all([
      signIn.evaluate(el => el.click()),
      page.waitForNavigation()
    ]);
}