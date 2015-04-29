import string
def word_count(phrase):
  counts = {}
  phrase = phrase.translate(None, string.punctuation).lower()
  words = [token for token in phrase.split(' ') if token.strip() != '']
  for word in words:
    if word in counts:
      counts[word] += 1
    else:
      counts[word] = 1
  return counts
