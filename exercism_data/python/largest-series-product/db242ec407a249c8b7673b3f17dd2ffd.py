from functools import reduce
import operator

def slices(numbers, nth):
    if nth < 1:
        return [[1]]
    if nth > len(numbers):
        raise ValueError()

    return [list(map(int, numbers[i:i + nth])) for i in
            range(0, len(numbers) - (nth - 1))]

def largest_product(numbers, nth):
    return max([reduce(operator.mul, slice) for slice in slices(numbers, nth)])
