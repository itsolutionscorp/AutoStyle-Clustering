__author__ = 'ansar_000'
inp = input("Input a DNA strand:- ")
inp2 = input("Input a 2nd DNA strand:- ")
hamming_distance = 0
dna1 = []
dna2 = []

for iter1 in inp:
    dna1.append(iter1)
for iter2 in inp2:
    dna2.append(iter2)

for x in range(0, len(dna1)):
    if dna1[x] == dna2[x]:
        pass
    elif dna1[x] != dna2[x]:
        hamming_distance = hamming_distance + 1

print("The Hamming distance between these two DNA strands is %s." % hamming_distance)
