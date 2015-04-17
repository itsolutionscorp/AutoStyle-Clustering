def detect_anagrams(word, candidates):
  return [candidate for candidate in candidates if word.lower() != candidate.lower() and sorted(word.lower()) == sorted(candidate.lower())]
