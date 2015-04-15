class DNA(object):

    def __init__(self, code):
        self._code = code

    def to_rna(self):
        translate = dict(G='C', C='G', T='A', A='U')
        return ''.join(translate[char] for char in self._code)
