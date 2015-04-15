def distance(seq1, seq2):
  '''
  Returns the hamming distance for the two passed dna sequences.

  The two sequences must be equal in length, otherwise an Exception is raised.
  '''
  if len(seq1) != len(seq2):
    raise Exception('Hamming')
  else:
    count = 0
    for i in range(len(seq1)):
      if seq1[i] != seq2[i]:
        count = count + 1
    return count
