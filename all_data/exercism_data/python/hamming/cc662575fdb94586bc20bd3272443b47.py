def distance(strandA, strandB):
    diff = 0
    for baseA, baseB in zip(strandA, strandB):
        if baseA != baseB:
            diff += 1
    return diff
        
