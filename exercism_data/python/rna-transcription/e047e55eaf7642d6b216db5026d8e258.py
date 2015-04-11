THYMIDINE = 'T'
URACIL = 'U'

class DNA(object):
    def __init__(self, code):
        self.code = code
    
    def to_rna(self):
        return self.code.replace(THYMIDINE, URACIL)
