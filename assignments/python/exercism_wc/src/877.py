def word_count(text):
  result  = {}
  for word in text.split():
    result[word] = result.get(word, 0) + 1
  return result
