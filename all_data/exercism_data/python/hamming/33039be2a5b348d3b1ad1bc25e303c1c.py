
def distance(sequence1, sequence2):
    # Create a sentinel variable to track letter position.
    s = -1

    # Create the variable that will hold the Hamming Distance value.
    distance = 0

    # Iterate over all letters in "sequence1."
    for letter in sequence1:
        # Increment the sentinel value "s."
        s+=1
        # If letters at position s are not equivalent, increase "distance."
        if sequence1[s] != sequence2[s]:
            distance += 1

    return distance
