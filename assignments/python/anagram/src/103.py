def detect_anagrams(word, options):
  result = []
  # Same word is not an anagram
  options = [x for x in options if x.lower() != word.lower()]
  letters = ''.join(sorted(word.lower()))
  for option in options:
    anagram = ''.join(sorted(option.lower()))
    # If the sorted list of letters in two words are the same, presto!
    if letters == anagram:
      result.append(option)
  return result
