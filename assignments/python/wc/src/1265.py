import re
def word_count(phrase):
  dict = {}
  words = phrase.lower().split(' ')
  for word in words:
    word = re.sub('[^a-zA-Z0-9]', '', word)
    if word != '':
      if dict.get(word) == None:
        dict[word] = 1
      else:
        dict[word] += 1
  return dict
