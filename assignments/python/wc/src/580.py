import re
def word_count(string):
  words_map = {}
  for word in re.findall('\w+', string):
    words_map[word.lower()] = words_map.get(word.lower(), 0) + 1
  return words_map
