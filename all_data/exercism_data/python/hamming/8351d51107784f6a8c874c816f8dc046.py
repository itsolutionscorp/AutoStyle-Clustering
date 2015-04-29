def distance(str1, str2):
  cursor = 0
  hdist = 0
  while cursor < len(str1):
    if str1[cursor] != str2[cursor]:
      hdist += 1
    cursor += 1
  return hdist
