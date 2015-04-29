import re
def word_count(text):
  #text = text.replace("\n"," ")
  text = re.sub("\s+|\n", " ",text)
  words = text.split(" ")
  count = dict()

  for word in words:
    if not word in count.keys():
      count[word] = 1
    else:
      count[word] += 1

  return count
