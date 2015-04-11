def distance(A, B):
    return sum(A[i] != B[i] for i, a in enumerate(A)) # Hamming Distance is only defined on sequences of the same length
