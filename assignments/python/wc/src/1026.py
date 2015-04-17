def word_count(text):
  word_dict = {}
  words = text.split()
  for word in words:
    if word in word_dict:
      word_dict[word] += 1
    else:
      word_dict[word] = 1
  return word_dict
