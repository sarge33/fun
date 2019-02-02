class PartitionSet {

  static boolean canPartition(int[] num) {
    //TODO: Write - Your - Code

    return checkNaive2(num, 0, 0, 0,  sum(num) / 2);
  
    //return checkNaive1(num, 0, new int[num.length-1], 0, new int[num.length-1], 0);
  }
  
  static int sum(int[]a){
    int sum = 0;
    for(int i: a){
      sum += i;
    }
    return sum;
  }
  static boolean checkNaive2(int[] num, int i, int sumA, int sumB, int max) {
    if(sumA > max || sumB > max) return false;
     if(i == num.length){
      if(sumA== sumB)
        return true;
      return false;
    }
    // sumA has nums[i]
    boolean b1 = checkNaive2(num, i+1, sumA+num[i], sumB, max);
    if(b1) return true;
    // sumB has nums[i]
    boolean b2 = checkNaive2(num, i+1, sumA, sumB+num[i], max);
    return b2;
  }
  static boolean checkNaive1(int[] num, int i, int[]a, int ia, int []b, int ib) {
    if(i == num.length){
      if(sum(a) == sum(b))
        return true;
      return false;
    }
    boolean b1 = false;
    boolean b2 = false;
    if(ia < a.length) {
      a[ia] = num[i];
      b1 = checkNaive1(num, i+1, a, ia+1, b, ib);
      a[ia] = 0;
    }
    if(ib < b.length) {
      b[ib] = num[i];
      b2 = checkNaive1(num, i+1, a, ia, b, ib+1);
      b[ib] = 0;
    }
    return b1 || b2;
  }
}
