def word_count(s):
  import string
  result = {}

  for word in s.split(' '):
    for letter in word:
      if letter in string.punctuation:
        word = word.replace(letter, '')
      elif letter == '' or letter == ' ':
        word.replace(letter, '')
    w = word
    if w.lower() in result.keys():
      result[w.lower()] = result[w.lower()] + 1
    else:
      result.update({w.lower() : 1})
  return result
