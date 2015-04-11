import re

def to_rna(dna_strand):
    if isinstance(dna_strand, basestring):
        if re.match('[ACGT]+', dna_strand):
            rna_strand = []
            for nucleotide in dna_strand:
                if nucleotide == 'G':
                    rna_strand.append('C')
                elif nucleotide == 'C':
                    rna_strand.append('G')
                elif nucleotide == 'T':
                    rna_strand.append('A')
                elif nucleotide == 'A':
                    rna_strand.append('U')
            return ''.join(rna_strand)

        else:
            return None
    else:
        return None
