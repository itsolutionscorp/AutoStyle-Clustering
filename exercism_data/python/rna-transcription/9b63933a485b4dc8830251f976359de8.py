class DNA:
    THYMIDINE = 'T'
    URACIL    = 'U'

    def __init__(self, string):
        self.string = string

    def to_rna(self):
        return self.string.replace(self.THYMIDINE, self.URACIL)
