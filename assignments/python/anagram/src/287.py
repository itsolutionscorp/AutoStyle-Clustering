def detect_anagrams(word, words):
  array = []
  for iword in words:
    flag = 0
    if sorted(word.lower()) == sorted(iword.lower()):
          flag = 1 
    if flag and word.lower() != iword.lower():
      array.append(iword)
  
  return array
