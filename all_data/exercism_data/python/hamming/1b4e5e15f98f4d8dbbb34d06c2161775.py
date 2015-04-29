def hamming(DNA1,DNA2):
    diff=0
    for x,y in zip(DNA1,DNA2):
        if x != y :
            diff += 1
    diff += abs(len(DNA1)-len(DNA2))
    return diff
