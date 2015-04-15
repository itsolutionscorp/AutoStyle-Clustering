import string

def word_count(s):
  # remove punctuation
  table = string.maketrans("", "")
  s = s.translate(table, string.punctuation)
  # transform to lowercase and get list of words
  words = s.lower().split()
  # create dict with count for each word set to 0
  counter = dict.fromkeys(words, 0)
  for word in words:
    counter[word] += 1
  return counter
  
