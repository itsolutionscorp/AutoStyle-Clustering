# Calculates the hamming distance between two homologous strands of DNA


def distance(first_string, second_string):
    # Initialize differences
    differences = 0

    # Check whether the two strings are the same length
    if len(first_string) != len(second_string):
        return False    # seems like this should be an exception

    # Iterate over both strings, checking each pair
    for i in xrange(len(first_string)):
        if first_string[i] != second_string[i]:
            differences += 1

    return differences
