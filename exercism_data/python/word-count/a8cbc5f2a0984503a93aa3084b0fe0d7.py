def word_count(text):
  words = text.split()
  summary = {}
  count = 0
  for key in words:
     if key not in summary:
         summary[key] = 1
     else:
         summary[key] = summary.get(key) + 1
  
  return summary
