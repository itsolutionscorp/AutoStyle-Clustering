def detect_anagrams(word, word_list):
  result = []
  for w in word_list:
    if w.lower() == word.lower():
      continue
    elif len(word) != len(w):
      continue
    else:
      if ''.join(sorted(w.lower())) == ''.join(sorted(word.lower())):
        result.append(w)
  return result
