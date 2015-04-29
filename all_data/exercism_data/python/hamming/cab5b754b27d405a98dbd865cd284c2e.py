def distance(strand1, strand2):
    differences = [char for i, char in enumerate(strand1) if char != strand2[i]]
    return len(differences)
