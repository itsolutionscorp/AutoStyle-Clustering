from functools import reduce
import operator


def slices(digits, grouping):
    if (grouping == 0):
        raise ValueError("Grouping cannot be 0.")
    elif (grouping > len(digits)):
        raise ValueError("Grouping larger than len(digits).")
    else:
        result = []
        dig_list = []
        for d in digits:
            dig_list.append(int(d))
        for i in range(len(dig_list)-grouping+1):
            result.append(dig_list[i:i+grouping])
        return result


def prod(iterable):
    return reduce(operator.mul, iterable, 1)


def largest_product(digits, grouping):
    if (grouping == 0):
        return 1
    series = slices(digits, grouping)
    result = 0
    for s in series:
        if (prod(s) > result):
            result = prod(s)
    return result