# dna.py

def to_rna(dna):
    """ Returns the RNA complement to 'dna' """

    # Define the complementary nucleotides
    transcriber = {'A': 'U', 'T': 'A', 'G': 'C', 'C': 'G'}

    # Create the RNA sequence using 'transcriber' and return it
    return ''.join([transcriber[n] for n in dna])
