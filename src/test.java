public class test {
    public static void main(String[] args) {
        DB db = new DB();
        System.out.println(db.init());
        System.out.println(db.insert("daniel", "daniel", "daniel@dan.com"));
    }
}
