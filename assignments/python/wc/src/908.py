import string
def word_count(s):
  table = string.maketrans("", "")
  s = s.translate(table, string.punctuation)
  words = s.lower().split()
  counter = dict.fromkeys(words, 0)
  for word in words:
    counter[word] += 1
  return counter
