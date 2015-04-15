def distance(strand_1, strand_2):
  result = len(strand_1)
  counter = 0
  for letter in strand_1:
    if strand_1[counter] == strand_2[counter]:
      result -= 1
    counter  += 1
  return result
