class DNA(object):
    def __init__(self, source):
        self.source = source

    def to_rna(self):
        return self.source.replace("T", "U")
