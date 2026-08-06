<h2><a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array">34. Find First and Last Position of Element in Sorted Array</a></h2>

<p>Given an array of integers <code>nums</code> sorted in non-decreasing order, find the starting and ending position of a given <code>target</code> value.</p>

<p>If <code>target</code> is not found in the array, return <code>[-1, -1]</code>.</p>

<p>You must&nbsp;write an algorithm with&nbsp;<code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [5,7,7,8,8,10], target = 8
<strong>Output:</strong> [3,4]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [5,7,7,8,8,10], target = 6
<strong>Output:</strong> [-1,-1]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [], target = 0
<strong>Output:</strong> [-1,-1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code> is a non-decreasing array.</li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= target&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>


---

# Find First and Last Position of Element in Sorted Array | Explained

## Approach 1: Modified Binary Search with Bias Flag

### Intuition

In a standard binary search, as soon as we find an element equal to the `target`, we immediately return its index. However, because the input array is sorted and can contain duplicate values, finding *an* index containing `target` does not guarantee that it is the *first* or *last* occurrence.

Think of it like looking for the first and last page of a chapter in a textbook. If you open to a page in the middle of that chapter, you know the chapter exists. To find where it begins, you keep flipping backward until you hit the previous chapter. To find where it ends, you keep flipping forward. 

Instead of flipping one page at a time (which would take $O(n)$ time in the worst case), we can stick to binary search ($O(\log n)$). When we land on `nums[mid] == target`, we record `mid` as a potential boundary index. Then, depending on whether we are looking for the left or right boundary, we narrow our search space to continue checking the left half or the right half.

### Algorithm Visualized

```mermaid
graph TD
    A[Start Binary Search] --> B{l <= r?}
    B -- No --> C[Return candidate index i]
    B -- Yes --> D["Calculate mid = (l + r) / 2"]
    D --> E{nums[mid] relative to target}
    E -- target < nums[mid] --> F[r = mid - 1]
    E -- target > nums[mid] --> G[l = mid + 1]
    E -- target == nums[mid] --> H[Record candidate i = mid]
    H --> I{leftbias is true?}
    I -- Yes --> F
    I -- No --> G
    F --> B
    G --> B
```

### Approach

1. Define a helper function `binarysearch(int[] nums, int target, boolean leftbias)` that returns the boundary index of the target.
2. Initialize two pointers, `l = 0` and `r = nums.length - 1`, along with a variable `i = -1` to track the most recently found target index.
3. While `l <= r`:
   - Compute `mid = (l + r) / 2`.
   - If `target < nums[mid]`, the target must lie in the left half, so set `r = mid - 1`.
   - If `target > nums[mid]`, the target must lie in the right half, so set `l = mid + 1`.
   - If `nums[mid] == target`, we found a match. Record `i = mid`.
     - If `leftbias` is `true`, we want the starting position. Continuously search leftward by updating `r = mid - 1`.
     - If `leftbias` is `false`, we want the ending position. Continuously search rightward by updating `l = mid + 1`.
4. Run `binarysearch` twice:
   - Once with `leftbias = true` to get the leftmost index.
   - Once with `leftbias = false` to get the rightmost index.
5. Return both indices in a 2-element array.

### Detailed Code Analysis

Let us analyze the exact implementation provided.

```java
private int binarysearch(int[]nums,int target,boolean leftbias){
    int l=0;
    int r=nums.length-1;
    int i=-1;
```
Here, `l` and `r` define our search space boundaries. `i` acts as a fallback default initialized to `-1`. If `target` is not present anywhere in `nums`, the loop will terminate and return `-1`.

```java
    while(l<=r){
        int mid=(l+r)/2;
```
This is the main search loop. Note that `(l + r) / 2` calculates the mid-point. In languages with fixed integer limits, calculating `l + (r - l) / 2` is preferred to avoid potential integer overflow for large arrays.

```java
        if(target<nums[mid]){
            r=mid-1;
        }
        else if(target>nums[mid]){
            l=mid+1;
        }
```
Standard binary search logic: if the target is smaller than the middle element, discard the right half by setting `r = mid - 1`. If it is larger, discard the left half by setting `l = mid + 1`.

```java
        else{
            i=mid;
            if(leftbias){
                r=mid-1;
            }
            else{
                l=mid+1;
            }
        }
```
This is the core logic modification:
- When `nums[mid] == target`, we store `mid` in `i`.
- If `leftbias` is `true`, we attempt to find an even earlier occurrence of `target` by moving our right pointer `r` to `mid - 1`.
- If `leftbias` is `false`, we attempt to find a later occurrence by moving our left pointer `l` to `mid + 1`.

```java
    return i;
}
```
After the search range collapses (`l > r`), `i` holds the extreme index found (either leftmost or rightmost), or `-1` if the element was never found.

```java
public int[] searchRange(int[] nums, int target) {
    int left=binarysearch(nums,target,true);
    int right=binarysearch(nums,target,false);
    int arr[]=new int[2];
    arr[0]=left;
    arr[1]=right;
    return arr;
}
```
The driver function executes two independent binary searches. The first search finds the lower bound (`leftbias = true`), and the second finds the upper bound (`leftbias = false`). Finally, it stores both results in a size-2 array and returns it.

### Code

```java
class Solution {
    private int binarysearch(int[] nums, int target, boolean leftbias) {
        int l = 0;
        int r = nums.length - 1;
        int i = -1;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            if (target < nums[mid]) {
                r = mid - 1;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                i = mid;
                if (leftbias) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return i;
    }

    public int[] searchRange(int[] nums, int target) {
        int left = binarysearch(nums, target, true);
        int right = binarysearch(nums, target, false);
        
        int arr[] = new int[2];
        arr[0] = left;
        arr[1] = right;
        return arr;
    }
}
```

### Complexity

- **Time Complexity:** $O(\log n)$. Executing a binary search takes logarithmic time, $O(\log n)$. Since we run two separate binary searches sequentially, the total runtime is $O(\log n) + O(\log n) = O(\log n)$, where $n$ is the number of elements in `nums`.
- **Space Complexity:** $O(1)$. The algorithm runs in constant auxiliary space because it only uses integer pointer variables (`l`, `r`, `mid`, `i`) and returns a fixed-size array of size 2.

## Follow-up Questions

### 1. What happens if `l + r` causes integer overflow, and how do we fix it?
In Java, `int` is a 32-bit signed integer with a maximum value of $2^{31}-1$ (2,147,483,647). If `l` and `r` are both very large indices (e.g., greater than $2^{30}$), their sum `l + r` can exceed the maximum integer boundary and overflow into a negative number, leading to an `ArrayIndexOutOfBoundsException`. 

To fix this, calculate the midpoint using subtraction instead of addition:
```java
int mid = l + (r - l) / 2;
```

### 2. Can we optimize this to only do one binary search instead of two?
Technically, we can run a single binary search to find the lower bound (first occurrence of `target`). If the target exists, we can then run a second binary search to find the lower bound of `target + 1`. The index right before that lower bound will be the upper bound of `target`. However, this still fundamentally performs two binary search operations ($O(\log n)$ each), so the asymptotic complexity remains unchanged.