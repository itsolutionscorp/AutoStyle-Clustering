def to_rna(strand):
  rnaS = ''
  dnaL = ['G','C','T','A']
  rnaL = ['C','G','A','U']

  for n in strand:
    complement = rnaL[dnaL.index(n)]
    rnaS = rnaS + complement

  return rnaS
