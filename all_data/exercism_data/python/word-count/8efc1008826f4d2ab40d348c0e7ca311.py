def word_count(phrase):
  count_dict = {}
  phrase = phrase.replace('\n',' ')
  for word in phrase.split(' '):
    if not word or word==' ':
      continue
    try:
      count_dict[word]+=1
    except KeyError:
      count_dict[word] = 1
  return count_dict
