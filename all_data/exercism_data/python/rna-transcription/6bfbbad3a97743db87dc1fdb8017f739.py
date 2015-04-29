#!/usr/bin/env python3

def to_rna(dna_strand):
    rna_map = { "G":"C", "C":"G", "T":"A", "A":"U" }
    rna_strand = ""
    for nucleotide in dna_strand:
        rna_strand += rna_map[nucleotide]

    return rna_strand

if __name__ == '__main__':
    import sys
    print(to_rna(sys.argv[1]))
