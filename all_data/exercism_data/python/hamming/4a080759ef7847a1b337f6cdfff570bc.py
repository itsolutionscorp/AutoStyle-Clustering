def distance(strand1, strand2):
  hamming_distance = 0 

  strand1_length = len(strand1)
  strand2_length = len(strand2)

  if (strand1_length == strand2_length):
    for position in range(strand1_length):
      if not (strand1[position] == strand2[position]):
        hamming_distance += 1 
  else:
    return None 

  return hamming_distance
