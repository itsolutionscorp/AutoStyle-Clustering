def word_count(word):
  total = {}
  word = word.replace("\n", " ")
  words = word.split(" ")
  for foo in words:
    if foo != "":
      if foo in total.keys():
        total[foo] += 1
      else:
        total[foo] = 1
  return total
