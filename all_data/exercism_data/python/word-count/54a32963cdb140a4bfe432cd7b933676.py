def word_count(sentences):
  arr_of_word = sentences.split(' ')
  dict_word = {}

  for word in arr_of_word:
    dict_word[word] = (1 + dict_word[word]) or 1

  return dict_word
