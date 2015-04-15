def slices(nums, length):
    series = list()

    if length > len(nums) or not length: raise ValueError

    for i in range(len(nums)):
        slice = [int(x) for x in nums[i:length + i]]
        if len(slice) == length:
            series.append(slice)
    return series
