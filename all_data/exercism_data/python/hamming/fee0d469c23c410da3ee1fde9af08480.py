def distance(dna_strand1, dna_strand2):
    
    hamming_distance_count = 0

    def are_letters_different(letter_pair):
        return letter_pair[0] != letter_pair[1]

    def get_letter_pairs(string1, string2):
        length_of_strings = len(string1)
        return [[string1[x], string2[x]] for x in range(length_of_strings)]


    letter_pairs_of_dna_strand1_and_dna_strand2 = get_letter_pairs(
        dna_strand1, dna_strand2)

    for letter_pair in letter_pairs_of_dna_strand1_and_dna_strand2:
        if are_letters_different(letter_pair):
            hamming_distance_count += 1

    return hamming_distance_count
