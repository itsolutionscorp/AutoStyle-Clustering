def word_count(words):
  words_list = {}
  words = words.replace('\n', ' ')
  words = words.split(' ')
  for word in words:
    if (word):
      if word not in words_list:
        words_list[word] = 1
      else:
        words_list[word] += 1
  return words_list
