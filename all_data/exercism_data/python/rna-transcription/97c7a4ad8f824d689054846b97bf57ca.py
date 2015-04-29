## I decided to use a loop that goes through a listed version
## of the function's input. It checks each value in the list.
## Each value then adds an element to a new list. Once it's
## done the list is then joined together to become one string.

def to_rna(dna):
    rna_list = []
    for x in list(dna):
        if x == 'A':
            rna_list.append('U')
        elif x == 'T':
            rna_list.append('A')
        elif x == 'C':
            rna_list.append('G')
        else:
            rna_list.append('C')
    rna_list = ''.join(rna_list)
    return rna_list
