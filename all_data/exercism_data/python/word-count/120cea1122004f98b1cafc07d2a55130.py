import string

def word_count(text):
  result = {}
  for word in text.lower().translate(None, string.punctuation).split():
    result[word] = result[word] + 1 if word in result else 1
  return result
