import string
def word_count(phrase):
  words = phrase.strip().replace("\n", " ").split(" ")
  words = filter(None, words)
  count = {}
  for word in words:
    if word not in count:
      count[word] = 1
    else:
      count[word] += 1
  return count
