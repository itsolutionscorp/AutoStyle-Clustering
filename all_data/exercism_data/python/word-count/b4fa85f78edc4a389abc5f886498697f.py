def word_count(sentence):
  count = {}

  for word in sentence.split():
    count[word] = count.get(word, 0) + 1

  return count
