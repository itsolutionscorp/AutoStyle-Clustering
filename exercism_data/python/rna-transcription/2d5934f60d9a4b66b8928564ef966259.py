def convert_one_letter(dna_letter):
    values = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return values[dna_letter]


def to_rna(dna_strand):
    listed_output = ""
    for letter in list(dna_strand):
        listed_output += convert_one_letter(letter)
    return listed_output
