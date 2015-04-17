def slices(input, length):
    if length < 1:
        raise ValueError("Invalid length given. Please make sure length is a positive integer.")
    if length > len(input):
        raise ValueError("Invalid length given. Please make sure length is less than or equal to the number of digits given.")
    slices = []
    digits = [ int(digit) for digit in input ]
    for i in xrange(len(digits) - length + 1):
        slices.append(digits[i:i+length])
    return slices
