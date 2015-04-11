def distance(seq1, seq2):
  '''
  Returns the hamming distance for the two passed dna sequences.

  The two sequences must be equal in length, otherwise an Exception is raised.
  '''
  if len(seq1) != len(seq2):
    raise Exception('Hamming')
  else:
    return len(filter(lambda z: z[0] != z[1], zip(seq1, seq2)))
