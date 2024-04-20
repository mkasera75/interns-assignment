public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        String str = "recear";
        char[] arr = str.toCharArray();
        String str1="";
        for(int i=str.length()-1;i>=0;i--){
            str1+=arr[i];
        }
        if(str.equals(str1)){
            System.out.println("Palindrome");
        }
        else{
            System.out.println("Not a Palindrome");
        }
    }
}
