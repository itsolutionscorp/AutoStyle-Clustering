def distance(stringA, stringB):
    if len(stringA) != len(stringB):
        print "Hamming only possible with strings of equal length"
    else:
        hammingdiff = 0
        for i in range(len(stringA)):
            if stringA[i] != stringB[i]:
                hammingdiff += 1

        return hammingdiff
            
