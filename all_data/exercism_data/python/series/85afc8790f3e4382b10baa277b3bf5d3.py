def slices(nums, l):
    n = [int(i) for i in nums]
    if l > len(nums) or l < 1:
        raise ValueError, 'Don\'t be a twat'
    else:
        return [n[i : i + l] for i in range(len(nums) - l + 1)]
