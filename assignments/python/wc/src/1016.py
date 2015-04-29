def word_count(words):
    dic = {}
    for word in words.split():
      if word not in dic:
         dic[word] = 0
      dic[word] += 1
    return dic
