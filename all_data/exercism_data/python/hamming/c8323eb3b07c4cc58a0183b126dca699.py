def distance(dnaString1, dnaString2):
    counter = 0
    if len(dnaString1) == len(dnaString2):
        for i in range(len(dnaString1)):
            if dnaString1[i] != dnaString2[i]:
                counter += 1
    else:
        print "distance() input DNA string lengths do not match, returning 0"

    return counter
