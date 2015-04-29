class DNA(object):
    thymidine = "T"
    uracil = "U"
    
    def __init__(self, string):
        self.__string = string

    def to_rna(self):
        return self.__string.replace(self.thymidine, self.uracil)
