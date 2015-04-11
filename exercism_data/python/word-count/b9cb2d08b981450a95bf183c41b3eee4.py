def word_count(text):
  words = text.split()
  summary = {}
  count = 0
  while words:
    word = words[-1]
    if word not in summary:
      summary[word] = 1
    else:
      key = word
      count = summary[word] + 1
      summary.update({key: count})
      count = 0

    del words[-1]



  return summary
