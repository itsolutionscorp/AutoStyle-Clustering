class DNA(str):
    def to_rna(self):
        return self.replace("T", "U")
