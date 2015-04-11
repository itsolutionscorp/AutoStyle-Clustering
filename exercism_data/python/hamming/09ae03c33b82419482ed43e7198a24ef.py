def hamming(strandX, strandY):
    distance = abs(len(strandX)-len(strandY))
    for baseX, baseY in zip(strandX,strandY):
        if not baseX == baseY:
            distance += 1
    return distance
