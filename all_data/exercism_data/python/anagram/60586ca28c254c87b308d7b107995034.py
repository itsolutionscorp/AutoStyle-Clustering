def detect_anagrams(word, candidates):
  word = word.lower()
  canon = sorted(word) 
  # Who says a word can't be its own anagram? That's ridiculous. I'm doing this under protest.
  return [cand for cand in candidates if sorted(cand.lower()) == canon and cand.lower() != word]
