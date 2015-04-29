import re
def word_count(phrase):
  wcount = {}
  for word in re.split('\s+', phrase):
     if word not in wcount:
      wcount[word] = 1
     else:
      wcount[word] += 1
  return wcount
