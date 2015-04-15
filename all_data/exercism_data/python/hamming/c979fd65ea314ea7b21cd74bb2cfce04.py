import sys

def hamming(sequence1,sequence2):
    sequence1 = list(sequence1)
    sequence2 = list(sequence2)

    min_sequence_len = min (len(sequence1), len(sequence2)) 
    sequence_len_dif = abs( len(sequence1) - len(sequence2) )

    distance = 0

    for x in range (0, min_sequence_len ):
        if sequence1[x] != sequence2[x]:
            distance += 1

    distance += sequence_len_dif
    return distance

if __name__ == '__main__':
    print hamming(sys.argv[1],sys.argv[2])
