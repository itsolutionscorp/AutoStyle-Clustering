def slices(nums, l):
    n = [int(i) for i in nums]
    if l > len(nums):
        raise ValueError, 'Don\'t be a twat'
    else:
        return [n[i : i + l] for i in range(len(nums) - l + 1)]

def product(nums):
    pdt = 1
    for i in nums:
        pdt = pdt * i
    return pdt

def largest_product(series, n):
    sls = slices(series, n)
    n = 1
    for i in sls:
        if product(i) > n:
            n = product(i)
    return n

if __name__ == '__main__':
    print slices('0123456', 3)
    print product([1,2,3])
    print largest_product('0123456', 3)
