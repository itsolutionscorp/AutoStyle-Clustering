def cached_result(func):
    results = {}
    def cacher(*args):
        if args not in results:
            results[args] = func(*args)

        return results[args]

    return cacher


class DNA(object):

    def __init__(self, strand):
        self.strand = strand

    @cached_result
    def nucleotide_counts(self):
        counts = { 'A': 0, 'C': 0, 'G': 0, 'T': 0 }

        for n in self.strand:
            try:
                counts[n] += 1
            except KeyError:
                raise ValueError(n + " is not a nucleotide.")

        return counts

    def count(self, nucleotide):
        if nucleotide == 'U':
            return 0

        try:
            return self.nucleotide_counts()[nucleotide]
        except KeyError:
            raise ValueError(nucleotide + " is not a nucleotide.")            

        
