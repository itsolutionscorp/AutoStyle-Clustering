import re
def word_count(text):
  result  = {}
  for word in re.split('[ \n]', text):
    if len(word) != 0:
      if word in result:
        result[word] = result[word] + 1
      else:
        result[word] = 1
  return result
