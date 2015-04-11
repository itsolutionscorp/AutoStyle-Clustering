# Return all the possible consecutive number series of length n in the string
def slices(digits,n):
    # Store the length of the string of digits, we'll need it several times
    digits_length = len(digits)

    # Validate the inputs
    if not validate_param(digits,basestring) or not validate_param(n,(int,long)) or \
        not validate_range_of_values(n, range(1,digits_length+1)) or \
            not validate_range_of_values(digits,[str(d) for d in range(10)]):

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

# Check that the object is not None and that it is the right type
def validate_param(object, type):
    return object is not None and isinstance(object,type)

# Check that the object's value falls within its valid range
def validate_range_of_values(object,possible_values):
    # if the object is a string, inspect it character by character
    if isinstance(object, basestring):
        return all([c in possible_values for c in object])
    return object in possible_values
