class DNA(object):
    def __init__(self,sequence):
        self.sequence=sequence

    def transcribe(self,single):
        if single == "T":
            return "U"
        else:
            return single

    def to_rna(self):
        out=""
        for s in self.sequence:
            out=out+self.transcribe(s)
        return out
