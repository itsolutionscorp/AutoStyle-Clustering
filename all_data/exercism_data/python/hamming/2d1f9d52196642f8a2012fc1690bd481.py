def distance(dnaOne, dnaTwo):
    return sum (dnaOne[i] != dnaTwo[i] for i in range(len(dnaOne)))
