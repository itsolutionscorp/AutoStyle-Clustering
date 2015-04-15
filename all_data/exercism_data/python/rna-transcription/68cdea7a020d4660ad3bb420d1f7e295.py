def to_rna(dna):
    rna = ''
    for letter in dna:
       if letter == 'C':
            rna += 'G'

       elif letter == 'G':
           rna += 'C'


       elif letter == 'A':
           rna += 'U'

       elif letter == 'T':
           rna += 'A'
    return rna
