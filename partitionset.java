class PartitionSet {

  static boolean canPartition(int[] num) {
    return canPartitionBruteForoce(num);
  }
  static boolean canPartitionBruteForoce(int[] num) {
    return check(num, 0, new int[num.length-1], 0, new int[num.length-1], 0);
  }
  static int sum(int[]a){
    int sum = 0;
    for(int i: a){
      sum += i;
    }
    return sum;
  }
  static boolean check(int[] num, int i, int[]a, int ia, int []b, int ib) {
    if(i == num.length){
      if(sum(a) == sum(b))
        return true;
      return false;
    }
    boolean b1 = false;
    boolean b2 = false;
    if(ia < a.length) {
      a[ia] = num[i];
      b1 = check(num, i+1, a, ia+1, b, ib);
      a[ia] = 0;
    }
    if(ib < b.length) {
      b[ib] = num[i];
      b2 = check(num, i+1, a, ia, b, ib+1);
      b[ib] = 0;
    }
    return b1 || b2;
  }
}
