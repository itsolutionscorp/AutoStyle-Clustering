'''exer series'''

def slices(nums, size):
    '''nums is a string of digits, return list of all slices of len size'''

    num_nums = len(nums)
    if size <= 0 or size > num_nums:
        raise ValueError

    nums = [int(x) for x in nums]   # cast it into a list of ints
    results = []
    # don't need to eval those that go past the end of the list
    for start in range(num_nums - size + 1):
        results.append(nums[start:start+size])

    return results

def largest_product(series, how_many):
    '''return the largest product of how_many consecutive ints in the series'''

    # guard against more than possible
    if how_many > len(series):
        raise ValueError
    #default to 1 per tests
    largest = 1
    try:
        for nums in slices(series, how_many):
            answer = 1
            for num in nums:
                answer *= num
            if answer > largest:
                largest = answer
    except ValueError:
        pass

    return largest
