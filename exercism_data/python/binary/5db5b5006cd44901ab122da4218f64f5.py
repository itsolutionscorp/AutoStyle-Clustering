def parse_binary(binary_string):

    # Make a list of numbers out of the binary string
    binary_list = [cast_bool(i) for i in binary_string[::-1]]

    # Use the indices of the list as exponents, but only if
    # there's a 1 at the corresponding position in binary_list
    return sum(2 ** i for i in range(len(binary_list)) if binary_list[i])


def cast_bool(i):

    int_representation = int(i)

    if int_representation not in range(2):
        raise ValueError("Valid binary strings consist only of 0s and 1s.")
    else:
        return int_representation
