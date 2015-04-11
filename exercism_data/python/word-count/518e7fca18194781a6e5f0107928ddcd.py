def word_count(string):
  splits = string.replace('\n', ' ').split()
  werdz = {}
  for word in splits:
    if word in werdz:
      werdz[word] += 1
    else:
      werdz[word] = 1
  return werdz
