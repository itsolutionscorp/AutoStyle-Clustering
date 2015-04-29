import string
def word_count(s):
  normalized = [x.lower().translate(None, string.punctuation) for x in s.split(' ')]
  count = {}
  for word in normalized:
    if word:
      if word in count:
        count[word] += 1
      else:
        count[word] = 1
  return count
