def word_count(words):
  if words.find('\n') > 0:
    words = words.replace('\n', ' ')
  word = words.split(" ")
  dlist = []
  for i in word:
    dlist.append(i)
  dlist.sort()
  count = 0
  cnt = dlist.count('')
  word_c = ""
  Dict = {}
  keys = []
  vals = []
  while cnt < len(dlist):
    if cnt == dlist.count(''):
     word_c = dlist[cnt]
    elif dlist[cnt] != word_c:
      keys.append(word_c)
      vals.append(count)
      word_c = dlist[cnt]
      count = 0
    count += 1
    cnt += 1
  keys.append(dlist[len(dlist) - 1])
  vals.append(count)
  Dict = dict(zip(keys, vals))
  return Dict
