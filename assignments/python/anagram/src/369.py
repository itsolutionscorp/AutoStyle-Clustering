def detect_anagrams(word, candidates):
  anagrams = []
  word = word.lower()
  for candidate in candidates:
    candidate_l = candidate.lower()
    if (word != candidate_l and sorted(word) == sorted(candidate_l)):
      anagrams.append(candidate)
  return anagrams
