from operator import mul

def slices(num, size):
    if size > len(num):
        raise ValueError

    # Creates a List of Lists with nested list comprehension.
    # Loops through the string to get the initial index for our inner list.
    # Iterates over the proper slice of num converting the character into int.
    return [[int(n) for n in num[i:i+size]] for i in range(0, len(num)-size+1)]

def largest_product(series, size):
    largest = 1
    slice_list = slices(series, size)

    for item in slice_list:
        try:
            product = reduce(mul, item)
        except TypeError: # Happens if series is None
            product = 1
        if product > largest:
            largest = product

    return largest
