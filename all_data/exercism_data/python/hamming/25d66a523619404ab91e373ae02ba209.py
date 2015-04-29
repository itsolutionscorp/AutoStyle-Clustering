def distance(first_string, second_string):
    """function to calculate and return hamming distance.

    """
    result = 0
    for index, char in enumerate(first_string):
        if char != second_string[index]:
            result += 1
    return result
