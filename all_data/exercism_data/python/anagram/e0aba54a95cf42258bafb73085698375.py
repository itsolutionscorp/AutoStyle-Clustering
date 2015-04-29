def detect_anagrams(word, testwords):
  def hash(word):
    return sorted(word.lower())
  sword = hash(word)
  matches = []
  for testword in testwords:
    if (sword == hash(testword)) and (word.lower() != testword.lower()):
      matches.append(testword)
  return matches
