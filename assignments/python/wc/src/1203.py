def word_count(sentence):
  list_words = sentence.split()
  list_res = {}
  for word in list_words:
    coincidences = list_words.count(word)
    list_res[word] = coincidences
  return list_res
