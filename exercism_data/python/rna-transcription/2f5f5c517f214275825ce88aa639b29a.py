def to_rna(dna):

    final_rna = ''

    def get_rna_letter(dna_letter):
        
        rna_letter = ''

        if dna_letter == 'A': rna_letter = 'U'    

        if dna_letter == 'T': rna_letter = 'A'

        if dna_letter == 'G': rna_letter = 'C'

        if dna_letter == 'C': rna_letter = 'G'

        return rna_letter


    for dna_letter in dna:
        
        rna_letter = get_rna_letter(dna_letter)
        
        final_rna += rna_letter

    return final_rna
