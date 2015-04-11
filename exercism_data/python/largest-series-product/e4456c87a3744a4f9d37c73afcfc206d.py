from collections import deque
from itertools import islice
from functools import reduce
from operator import mul

def slices(number, slice_length):
    _validate_slice_len(number, slice_length)
    digits = [int(n) for n in number]
    number_of_slices = len(digits) - slice_length + 1
    return [
        digits[offset : offset+slice_length]
        for offset in range(number_of_slices)
    ]

def largest_product(number, slice_length):
    _validate_slice_len(number, slice_length)

    if not len(number):
        return 1 

    largest = 0
    digits = (int(n) for n in number)
    try:
        # Initialize sliding slice buffer.
        buf = deque(islice(digits, 0, slice_length), slice_length)

        while True: 

            # Find the first upcoming slice without a zero product.
            while 0 in buf:
                buf.append(next(digits))
            product = reduce(mul, buf)

            while True:

                # Keep track of the largest product seen.
                if product > largest:
                    largest = product

                # Move to the next position. When a zero is enountered, skip
                # over it and restart the loop.
                digit = next(digits)
                if digit == 0:
                   buf.extend(islice(digits, 0, slice_length))
                   break
 
                # Compute the product for the current slice, by modifying
                # the previous result.
                product = product * digit / buf[0]
                buf.append(digit)

    # Until we run out of digits.
    except StopIteration:
        return largest

def _validate_slice_len(number, slice_length):
    if (slice_length > len(number)):
        raise ValueError("Parameter 'slice_length' exceeds the number's length")
    if (len(number) and slice_length <= 0):
        raise ValueError("Parameter 'slice_length' must be larger than 0")
