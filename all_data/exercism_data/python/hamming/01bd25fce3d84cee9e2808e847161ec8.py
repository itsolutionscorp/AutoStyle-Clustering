# hamming.py

def hamming(a, b):
    return sum([1 for x, y in zip(a, b) if x != y]) + abs(len(a) - len(b))
