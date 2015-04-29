def word_count(text):
  text = text.replace("  ", "")
  text = text.strip()
  text = text.replace('\n', ' ').replace('\r', '')
  words = text.split(" ")
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
