class DNA:
    def __init__(self, strand):
        self.strand = strand
        self.dict = {"A": 0, "T": 0, "C": 0, "G": 0}
    def count(self, nucl):
        if nucl not in self.dict.keys() and nucl != "U":
            raise ValueError("%s is not a nucleotide." % nucl)
        if self.strand == "":
            return 0
        else:
            nucls = [x for x in self.strand]
            counter = 0
            for n in nucls:
                if nucl == n:
                    counter += 1
            return counter

    def nucleotide_counts(self):
        result = self.dict
        if self.strand == "":
            return self.dict
        else:
            result["A"] = self.count("A")
            result["T"] = self.count("T")
            result["C"] = self.count("C")
            result["G"] = self.count("G")
        return result
