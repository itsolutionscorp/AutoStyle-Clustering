__author__ = 'tracyrohlin'

def distance(string_1, string_2):
    hamming_number = 0
    for n in xrange(len(string_1)):
        if string_1[n] == string_2[n]:
            pass
        else:
            hamming_number += 1
    return hamming_number

print distance('GATACA', 'GCATAA')
