import sys

dna_to_rna = {}
dna_to_rna['G'] = 'C'
dna_to_rna['C'] = 'G'
dna_to_rna['T'] = 'A'
dna_to_rna['A'] = 'U'

def to_rna(input):
    output = ""
    for segment in list(input):
        output = output + dna_to_rna[segment]
    return output

if __name__ == '__main__':
    print to_rna(sys.argv[1])
