def slices(input, length):
    if length < 1:
        raise ValueError("Invalid length given. Please make sure length is a positive integer.")
    if length > len(input):
        raise ValueError("Invalid length given. Please make sure length is less than or equal to the number of digits given.")

    slices = []
    digits = [ int(digit) for digit in input ]

    for i in xrange(len(input) - length + 1):
        slices.append(digits[i:i+length])

    return slices


def largest_product(input, length):
    if length == 0:
        return 1
    series = slices(input, length)
    products = [ product(numbers) for numbers in series ]
    return max(products)


def product(numbers):
    product = 1
    for num in numbers:
        product *= num
    return product
