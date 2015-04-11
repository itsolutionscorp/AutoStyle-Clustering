class DNA:
    def __init__(self, stringOfDNA):
        self.stringOfDNA = stringOfDNA

    def to_rna(self):
        output = ""
        for char in self.stringOfDNA:
            if char == 'G':
                output += 'C'
            elif char == 'C':
                output += 'G'
            elif char == 'T':
                output += 'A'
            elif char == 'A':
                output += 'U'
        return output
