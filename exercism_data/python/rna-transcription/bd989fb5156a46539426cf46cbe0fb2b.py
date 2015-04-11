import re

# Given a DNA strand, return its RNA complement
def to_rna(dna_strand):

    # Check to see that the input is not None, and that it is a string
    if dna_strand is not None and isinstance(dna_strand, basestring):

        # Build a regular expression to validate the DNA strand
        # Possible values: A,C,G,T
        pattern = re.compile('[ACGT]+')

        # Check if the input matches the pattern
        if pattern.match(dna_strand):

            # create a list to store the new RNA strand
            rna_strand = []

            # Iterate across the DNA strand
            # Append the appropriate RNA nucleotide into the new strand
            for nucleotide in dna_strand:

                if nucleotide == 'G':
                    rna_strand.append('C')
                elif nucleotide == 'C':
                    rna_strand.append('G')
                elif nucleotide == 'T':
                    rna_strand.append('A')
                elif nucleotide == 'A':
                    rna_strand.append('U')

            # Merge all the RNA nucleotides into a single strand and return it
            return ''.join(rna_strand)

        else:
            # This input is not valid, it contains non-nucleotide characters
            return None
    else:
        # We didn't get a valid input
        return None
