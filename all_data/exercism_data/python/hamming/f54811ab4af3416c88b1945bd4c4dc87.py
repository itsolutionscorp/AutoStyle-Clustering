def distance(strand1, strand2):  
    return sum(char1 != char2 for char1, char2 in zip(strand1, strand2))
