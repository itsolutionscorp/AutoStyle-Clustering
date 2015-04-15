from functools import reduce
import operator


def largest_product(number, consecutive):
    largest = 1
    for sliced_number in slices(number, consecutive):
        product = reduce(operator.mul, sliced_number)
        if product > largest:
            largest = product
    return largest


def slices(number, consecutive):
    if len(number) < consecutive:
        raise ValueError
    sliced_numbers = []
    for i in range(len(number)):
        sliced_number = map(int, number[i:i+consecutive])
        if len(sliced_number) < consecutive:
            break
        sliced_numbers.append(sliced_number)
    return sliced_numbers
