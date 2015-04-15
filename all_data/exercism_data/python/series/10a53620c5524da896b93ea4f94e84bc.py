def slices(str,n):
    if not(1<=n<=len(str)):
        raise ValueError
               
    nums=[int(letter) for letter in str]
    return [nums[m:m+n] for m in range(len(nums)-n+1)]
