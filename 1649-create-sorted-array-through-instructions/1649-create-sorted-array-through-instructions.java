class BIT {
    int[] bit;
    BIT(int size) {
        bit = new int[size + 1];
    }
    int getSum(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & (-idx))
            sum += bit[idx];
        return sum;
    }
    int getSumRange(int left, int right) {
        return getSum(right) - getSum(left - 1);
    }
    void addValue(int idx, int val) {
        for (; idx < bit.length; idx += idx & (-idx))
            bit[idx] += val;
    }
};

class Solution {
    public int createSortedArray(int[] instructions) {
        int max = Arrays.stream(instructions).max().getAsInt(), MOD = 1000000007;
        BIT bit = new BIT(max);
        int cost = 0;
        for (int x : instructions) {
            int left = bit.getSumRange(0, x - 1);
            int right = bit.getSumRange(x + 1, max);
            cost = (cost + Math.min(left, right)) % MOD;
            bit.addValue(x, 1);
        }
        return cost;
    }
}