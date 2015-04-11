def hamming(string1, string2):
    count = 0
    # Set the maximum loops to the length of the shortest string.
    for i in range(0, len(min([string1, string2], key=len))):

        # Compare each item and increment the Hamming difference for each error
        if string1[i] != string2[i]:
            count += 1

    # Add any hamming error caused by a difference in string lengths.
    count += abs(len(string1)-len(string2))

    return count
