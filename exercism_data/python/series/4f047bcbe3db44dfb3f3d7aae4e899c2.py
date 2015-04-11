# Return all the possible consecutive number series of length n in the string
def slices(digits,n):
    # Store the length of the string of digits, we'll need it later
    digits_length = len(digits)

    # Validate the inputs
    if not isinstance(digits,basestring) or not isinstance(n,(int,long)) or \
            n not in range(1,digits_length+1) or not digits.isdigit():

        raise ValueError

    # Starting index, which will be used to progressively advance along the string
    start = 0

    # Resultant list of series
    result = []

    # Go through the string of digits for as long as possible
    while start + n <= digits_length:
        # Append the current series to the list of results
        result.append([int(d) for d in digits[start:start+n]])

        # Advance the starting index
        start += 1

    return result
