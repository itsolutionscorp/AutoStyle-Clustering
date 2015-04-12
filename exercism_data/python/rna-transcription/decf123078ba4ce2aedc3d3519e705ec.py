# DNA to RNA transcription

def to_rna(dna_strand):

    #init
    rna_strand = ''
    dna_strand = dna_strand.upper()
    nuc_pairs = {'A':'U', 'C':'G', 'G':'C', 'T':'A'}
    
    for nuc in dna_strand:
        rna_strand += nuc_pairs[nuc]
        
    return rna_strand


# ---- main

#init
permitted_nucs = ('g', 'G', 'c', 'C', 't', 'T', 'a', 'A')
valid_strand = False
rna_strand = ''

while not valid_strand:
    nuc_count = 0
    dna_strand = ''

    dna_strand = input('Enter DNA strand to transcribe (Enter to quit): ')
    if dna_strand == '':
        valid_strand = True
    else:
        for nuc_count in range(len(dna_strand)):
            if dna_strand[nuc_count] in permitted_nucs:
                valid_strand = True
            else:
                valid_strand = False
                nuc_count = len(dna_strand)
                print('INVALID INPUT - DNA strand can only consist of C, G, T, or A nucleotides. Please reenter.')

# get RNA transcription
rna_strand = to_rna(dna_strand)
print('\nDNA strand:', dna_strand.upper())
print('\nRNA transcription:', rna_strand)