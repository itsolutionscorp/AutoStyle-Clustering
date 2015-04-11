def hamming(one, two):
    ham = 0

    for i in range(0, max(len(one), len(two))):
        try:
            if one[i] != two[i]:
                ham+=1
        except IndexError:
            ham+=1
    return ham
