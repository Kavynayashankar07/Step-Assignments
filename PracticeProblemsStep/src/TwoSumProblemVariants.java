import java.util.*;

class Transaction {
    int amount;
    long time;

    Transaction(int amount, long time) {
        this.amount = amount;
        this.time = time;
    }
}

class Payment {
    int amount;
    String merchant;
    String account;

    Payment(int amount, String merchant, String account) {
        this.amount = amount;
        this.merchant = merchant;
        this.account = account;
    }
}

public class TwoSumProblemVariants {

    // 1. Classic Two Sum
    public static void twoSum(int arr[], int target) {

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<arr.length;i++){

            int complement = target - arr[i];

            if(map.containsKey(complement)){
                System.out.println("Two Sum Pair: " + complement + " + " + arr[i]);
                return;
            }

            map.put(arr[i],i);
        }

        System.out.println("No pair found");
    }


    // 2. Two Sum with Time Window (1 hour)
    public static void twoSumTimeWindow(List<Transaction> list, int target){

        HashMap<Integer,Transaction> map = new HashMap<>();

        for(Transaction t : list){

            int complement = target - t.amount;

            if(map.containsKey(complement)){

                Transaction prev = map.get(complement);

                if(Math.abs(t.time - prev.time) <= 3600){
                    System.out.println("Fraud Pair (within 1 hour): "
                            + complement + " + " + t.amount);
                }
            }

            map.put(t.amount,t);
        }
    }


    // 3. K-Sum (3-Sum example)
    public static void threeSum(int arr[], int target){

        Arrays.sort(arr);

        for(int i=0;i<arr.length-2;i++){

            int left = i+1;
            int right = arr.length-1;

            while(left < right){

                int sum = arr[i] + arr[left] + arr[right];

                if(sum == target){
                    System.out.println("3-Sum: "
                            + arr[i] + " " + arr[left] + " " + arr[right]);
                    left++;
                    right--;
                }
                else if(sum < target)
                    left++;
                else
                    right--;
            }
        }
    }


    // 4. Duplicate Payment Detection
    public static void detectDuplicatePayments(List<Payment> list){

        HashMap<String,String> map = new HashMap<>();

        for(Payment p : list){

            String key = p.amount + "-" + p.merchant;

            if(map.containsKey(key) && !map.get(key).equals(p.account)){
                System.out.println("Duplicate Payment Detected for merchant: "
                        + p.merchant);
            }

            map.put(key,p.account);
        }
    }


    public static void main(String[] args) {

        int transactions[] = {10,20,35,50,75};
        int target = 55;

        twoSum(transactions,target);
        List<Transaction> tlist = new ArrayList<>();

        tlist.add(new Transaction(20,1000));
        tlist.add(new Transaction(30,1500));
        tlist.add(new Transaction(40,5000));

        twoSumTimeWindow(tlist,50);
        int arr[] = {10,20,30,40,50};

        threeSum(arr,100);
        List<Payment> plist = new ArrayList<>();

        plist.add(new Payment(100,"Amazon","A1"));
        plist.add(new Payment(100,"Amazon","A2"));
        plist.add(new Payment(200,"Flipkart","A3"));

        detectDuplicatePayments(plist);
    }
}