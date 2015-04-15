class DNA(object):

    def __init__(self, dna):
        self.dna = dna
        
    def cache_result(func):
        """spice it up a bit"""
        rna = {}
        def cacher(self):
            if not self.dna in rna:
                rna[self.dna] = func(self);
                print 'here'

            return rna[self.dna];

        return cacher

    @cache_result
    def to_rna(self):
        return self.dna.replace('T', 'U')
        
