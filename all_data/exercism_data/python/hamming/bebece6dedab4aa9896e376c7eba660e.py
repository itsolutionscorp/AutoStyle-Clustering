import re

# Calculate the Hamming difference between two DNA strands
# This refers to the number of mutations between them
def hamming(dna_strand_1, dna_strand_2):

    # Validate that our inputs are not None and that they are indeed strings
    if dna_strand_1 is not None and isinstance(dna_strand_1, basestring) and \
        dna_strand_2 is not None and isinstance(dna_strand_2, basestring):

        # Build a regular expression to validate the DNA strand
        # Possible values: A,C,G,T
        pattern = re.compile('[ACGT]+')

        # Check if the input matches the pattern
        if pattern.match(dna_strand_1) and pattern.match(dna_strand_2):

            # Get the length of each strand to see if they don't match
            len_strand_1 = len(dna_strand_1)
            len_strand_2 = len(dna_strand_2)

            # Check if the lengths are not the same
            if len_strand_1 != len_strand_2:

                # Check which strand is shorter and add some dummy nucleotides to compensate
                if len_strand_1 < len_strand_2:
                    dna_strand_1 = dna_strand_1 + '#'*(len_strand_2 - len_strand_1)
                else:
                    dna_strand_2 = dna_strand_2 + '#'*(len_strand_1 - len_strand_2)

            # Initialize the result
            hamming_distance = 0

            # Iterate across both strands
            for i in range(max(len_strand_1,len_strand_2)):

                # Whenever there is a mismatch, add 1 to the result
                if dna_strand_1[i] != dna_strand_2[i]:
                    hamming_distance += 1

            return hamming_distance
        else:
            # Our inputs do not meet the required criteria
            return 0
    else:
        # Our inputs were not valid
        return 0
