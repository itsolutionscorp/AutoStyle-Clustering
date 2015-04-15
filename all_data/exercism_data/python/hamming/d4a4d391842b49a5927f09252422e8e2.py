# program for Exercism project Hamming

def distance(x, y):
    count = 0
    if len(x) == len(y):
        for c, d in zip(x, y):
            if c != d:
                count += 1

    return count
