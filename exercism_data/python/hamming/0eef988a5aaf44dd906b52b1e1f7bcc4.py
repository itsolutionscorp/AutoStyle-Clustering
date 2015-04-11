def hamming(first_sequence, second_sequence):
    count = 0
    if len(first_sequence) > len(second_sequence):
        second_sequence += '0' * (len(first_sequence) - len(second_sequence))
    else:
        first_sequence += '0' * (len(second_sequence) - len(first_sequence))
    for index in range(len(first_sequence)):
        if first_sequence[index] != second_sequence[index]:
            count += 1
    return count
