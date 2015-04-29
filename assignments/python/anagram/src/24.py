def detect_anagrams(word, pot_matches):
  word = word.lower()
  return filter(lambda w: sorted(word) == sorted(w.lower()) and word != w.lower(), pot_matches)
