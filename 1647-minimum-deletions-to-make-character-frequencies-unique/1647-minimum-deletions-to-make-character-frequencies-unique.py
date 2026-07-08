class Solution:
    def minDeletions(self, s: str) -> int:
        freq = [0] * 26
        
        for c in s:
            freq[ord(c) - ord('a')] += 1
        
        freq.sort()
        
        del_count = 0
        
        for i in range(24, -1, -1):
            if freq[i] == 0:
                break
            
            if freq[i] >= freq[i + 1]:
                prev = freq[i]
                freq[i] = max(0, freq[i + 1] - 1)
                del_count += prev - freq[i]
        
        return del_count