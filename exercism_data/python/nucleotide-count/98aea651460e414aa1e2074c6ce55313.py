nucs = ['A', 'G', 'C', 'T', 'U']
def check(n):
    if n not in nucs:
        raise ValueError(n + ' is not a nucleotide.')
# My implementation: string goes in, dictionary comes out. That is all that is 
# happening here. This is not a class. It is a function!
def count(s):
    c = {'A': 0, 'G': 0, 'C': 0, 'T': 0}
    for n in s:
        check(n)
        try:
            c[n] += 1
        except KeyError:
            c[n] = 1
    return c

# Satisfy exercism's class fetish:
class DNA:
    def __init__(self, s):
        self.c = count(s)
    def nucleotide_counts(self):
        return self.c
    def count(self, n):
        check(n)
        return self.c.get(n, 0)
