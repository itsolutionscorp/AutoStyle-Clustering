def hamming(s_1, s_2):
  return sum(1 for x, y in zip(s_1, s_2) if x != y) + abs(len(s_1) - len(s_2))
