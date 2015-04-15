class DNA(object):
    """Transcribes DNA"""
    def __init__(self, clave):
        self.clave = clave
        self.replacements = {
                'A' : 'U',
                'T' : 'A',
                'C' : 'G',
                'G' : 'C'}
    def to_rna(self):
        return "".join(self.replacements.get(d, d) for d in self.clave)
