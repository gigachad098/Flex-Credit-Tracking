export class User {
    id: string;
    email: string | null;
    avgSpending: number | null;
    dailySpending: number | null;

    constructor(id: string) {
        this.id = id;
        this.email = null;
        this.avgSpending = null;
        this.dailySpending = null;
    }

    public get getId() {
        return this.id;
    }

    public get getEmail() {
        return this.email;
    }

    public set setEmail(email: string) {
        this.email = email;
    }

    public get getDailySpending() {
        return this.dailySpending;
    }

    public set setDailySpending(dailySpending: number) {
        this.dailySpending = dailySpending;
    }


    public get getAvgSpending() {
        return this.avgSpending;
    }
    
    public set setAvgSpending(avgSpending: number) {
        this.avgSpending = avgSpending;
    }
}
