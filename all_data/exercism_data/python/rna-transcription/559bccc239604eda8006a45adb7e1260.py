class DNA:

    def __init__(self, nucleo):
        self.nucleo = nucleo
        self.response = {
            "A" : "U",
            "T" : "A",
            "C" : "G",
            "G" : "C"
        }


    def to_rna(self):
        output = ''
        for base in self.nucleo:
            output += self.getBasePair(base)
        return output

    def getBasePair(self, base):
        return self.response[base]
