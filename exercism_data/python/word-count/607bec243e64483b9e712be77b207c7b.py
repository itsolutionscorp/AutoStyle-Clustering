def word_count(input):
  words = input.split()
  ret = {}
  for word in words:
    ret[word] = ret[word] + 1 if word in ret else 1;
  return ret
