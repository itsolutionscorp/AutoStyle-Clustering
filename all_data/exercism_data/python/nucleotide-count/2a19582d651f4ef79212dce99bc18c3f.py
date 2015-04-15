from collections import Counter

class DNA(dict):
    def __init__(self, dna):
        self["A"] = self["T"] = self["G"] = self["C"] = 0
        counter = Counter(dna)
        self.update(counter)

    def count(self, nucl):
        try:
            return self[nucl] if nucl != "U" else 0
        except KeyError:
            raise ValueError("{} is not a nucleotide.".format(nucl))

    def nucleotide_counts(self):
        return self
