class DNA:
    def __init__(self, dna_string):
        self.dna_string = dna_string


    def to_rna(self):
        s = ""
        for char in self.dna_string:
            if char == "G":
                s+="C"
            elif char =="C":
                s+="G"
            elif char =="T":
                s+="A"
            elif char =="A":
                s+="U"
        return s
