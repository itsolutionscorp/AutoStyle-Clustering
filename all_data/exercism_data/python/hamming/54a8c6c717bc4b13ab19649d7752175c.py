def hamming(A, B):
    fill = abs(len(A) - len(B))
    return sum(1 for Ac, Bc in zip(A, B) if Ac != Bc) + fill
