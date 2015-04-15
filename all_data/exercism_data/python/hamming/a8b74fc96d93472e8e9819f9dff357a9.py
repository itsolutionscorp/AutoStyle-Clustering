def hamming(strandA, strandB):
    """Hamming test over two strands of DNA.
       Compare characters pairwise (up to the mimumim length), and left over characters count as different
    """
    lA = len(strandA)
    lB = len(strandB);
    minLen = min( lA, lB)
    d = max( lA, lB) - minLen
    for x,y in zip( strandA[:minLen], strandB[:minLen]):
       if x != y:
          d += 1
    return d




