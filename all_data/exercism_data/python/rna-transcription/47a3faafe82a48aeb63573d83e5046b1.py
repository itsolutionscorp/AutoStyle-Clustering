__author__ = 'Daniel'


class DNA(object):
    to_rna = ""
    def __init__(self, base):
        self.to_rna = base
        self.transcribe(self.to_rna)

    def transcribe(self, bases):
        listofbases = list(bases)
        for i in range(0, len(listofbases)):
            if listofbases[i] == 'G':
                listofbases[i] = 'C'
            elif listofbases[i] == "C":
                listofbases[i] = "G"
            elif listofbases[i] == "T":
                listofbases[i] = "A"
            elif listofbases[i] == "A":
                listofbases[i] = "U"
        self.to_rna = "".join(listofbases)
