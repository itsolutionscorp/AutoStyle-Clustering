class DNA:
    def __init__(self, input_dna):
        self.input_dna = input_dna

    def hamming_distance(self, compare_string):
        return len(
            [e for e in zip(self.input_dna,
                            compare_string) if e[0] != e[1]])
