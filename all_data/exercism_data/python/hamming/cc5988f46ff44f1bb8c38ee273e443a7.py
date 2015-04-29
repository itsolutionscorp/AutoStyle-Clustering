def is_different(letter1, letter2):
    return letter1 != letter2

def distance(seq1, seq2):
    distance = 0

    for index, _ in enumerate(seq1):
        if is_different(seq1[index], seq2[index]):
            distance += 1

    return distance
