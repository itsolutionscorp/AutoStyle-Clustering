def word_count(phrase):
  wordcount = {}
  for w in phrase.split(" "):
    w = filter(lambda c: c.isalpha() or c.isdigit(), w.lower())
    if len(w) > 0:
      if w in wordcount:
        wordcount[w] += 1
      else:
        wordcount[w] = 1
  return wordcount
