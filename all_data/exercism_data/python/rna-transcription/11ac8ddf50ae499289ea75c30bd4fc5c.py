'''
Converts DNA -> RNA
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
'''
def to_rna(dna):
  rna = ''
  for n in dna:
    rna += replace_nucleotide(n)
  return rna

def replace_nucleotide(n):
  return {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
    }.get(n, 'x')
