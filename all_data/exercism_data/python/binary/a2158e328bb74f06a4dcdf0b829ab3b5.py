def parse_binary(binary_string):

    validate_string(binary_string)

    # Make a list of numbers out of the binary string
    binary_list = [int(i) for i in binary_string[::-1]]

    # Use the indices of the list as exponents, but only if
    # there's a 1 at the corresponding position in binary_list
    return sum(2 ** i for i in range(len(binary_list)) if binary_list[i])


def validate_string(binary_string):
    if any(i not in {"0", "1"} for i in binary_string):
        raise ValueError("Valid binary strings consist only of 0s and 1s.")
