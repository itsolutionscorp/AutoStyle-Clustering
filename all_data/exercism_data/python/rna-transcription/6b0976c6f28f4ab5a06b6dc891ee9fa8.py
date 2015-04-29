"""
dna - a module for DNA transcription.
"""

def to_rna(sequence):
    """
    Convert a DNA sequence to it RNA equivalent.
    """

    # Lookup table.
    conversions = {'G': 'C',
                   'C': 'G',
                   'T': 'A',
                   'A': 'U'}

    # Make the equivalent string fetching converted nucleotides from the lookup
    # table via a generator.
    return ''.join(conversions[nucleotide] for nucleotide in sequence)
