def detect_anagrams(text, possible_anagrams):
  confirmed_anagrams = []
  for possible_anagram in possible_anagrams:
    lower_text, lower_possible_anagram = text.lower(), possible_anagram.lower()
    if lower_text == lower_possible_anagram:
      continue
    if ''.join(sorted(lower_text)) == ''.join(sorted(lower_possible_anagram)):
      confirmed_anagrams.append(possible_anagram)
  return confirmed_anagrams
