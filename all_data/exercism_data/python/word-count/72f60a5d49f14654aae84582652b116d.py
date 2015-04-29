def word_count(string):
  hash = {}

  for word in string.split():
    if word in hash:
      hash[word] = hash[word] + 1
    else:
      hash[word] = 1

  return hash
