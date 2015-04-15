def distance(strand1, strand2):
  count = 0
  for i in range(len(strand1)):
    if strand1[i] != strand2[i]:
      count += 1
  return count
