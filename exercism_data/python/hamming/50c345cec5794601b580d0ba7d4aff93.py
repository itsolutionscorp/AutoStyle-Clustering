def distance(first, second):
    differences = [(f, s) for f, s in zip(first,second) if f != s]
    return len(differences)
