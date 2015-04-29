def is_different(char1, char2):
    return char1 != char2

def distance(sequence1, sequence2):
    if len(sequence1) != len(sequence2):
        raise ValueError("Sequences must be same length.")

    sequences = zip(sequence1, sequence2)

    return sum(is_different(char1, char2) for char1, char2 in sequences)
