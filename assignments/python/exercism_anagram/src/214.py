def detect_anagrams(word, candidates):
  anagrams = []
  # Same word is not an anagram
  candidates = [x for x in candidates if x.lower() != word.lower()]
  for candidate in candidates:
    if sorted(word.lower()) == sorted(candidate.lower()):
      anagrams.append(candidate)
  return anagrams
