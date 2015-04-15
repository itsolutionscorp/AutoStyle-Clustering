def word_count(line):
  r = {}
  words = line.split()
  for word in words:
    if word not in r:
      r[word] = 1
    else:
      r[word] += 1
  return r
