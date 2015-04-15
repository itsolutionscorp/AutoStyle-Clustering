import re

def hamming(dna_strand_1, dna_strand_2):
    if isinstance(dna_strand_1, basestring) and isinstance(dna_strand_2, basestring):
        pattern = re.compile('[ACGT]+')

        if pattern.match(dna_strand_1) and pattern.match(dna_strand_2):
            len_strand_1 = len(dna_strand_1)
            len_strand_2 = len(dna_strand_2)
            hamming_distance = 0

            if len_strand_1 != len_strand_2:
                if len_strand_1 < len_strand_2:
                    dna_strand_1 = dna_strand_1 + '#'*(len_strand_2 - len_strand_1)
                else:
                    dna_strand_2 = dna_strand_2 + '#'*(len_strand_1 - len_strand_2)

            for i in range(max(len_strand_1,len_strand_2)):
                if dna_strand_1[i] != dna_strand_2[i]:
                    hamming_distance += 1

            return hamming_distance
        else:
            return 0
    else:
        return 0
