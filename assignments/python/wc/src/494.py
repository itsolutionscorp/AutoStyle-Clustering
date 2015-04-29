def word_count(string):
  count = {}
  for word in string.split():
    if word in count:
      count[word] += 1
    else:
      count[word] = 1
  return count
