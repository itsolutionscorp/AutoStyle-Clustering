#Exercism activity #5

def distance(sequence1, sequence2):

    #Cutting the longer sequence down to ensure two of equal length
    shorterlength = min(len(sequence1), len(sequence2))
    sequence1 = sequence1[:shorterlength]
    sequence2 = sequence2[:shorterlength]

    #Iterating through characters of each sequence simultaneuously, checking for differences
    count = 0

    for i in range(shorterlength):
        if sequence1[i] != sequence2[i]:
            count += 1

    return count
