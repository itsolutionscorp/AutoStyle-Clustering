def detect_anagrams(target, words):
  output = []
  target = target.lower()
  target_letter_counts = get_char_counts(target)
  output = [word for word in words if target_letter_counts == get_char_counts(word.lower()) and target not in word.lower()]
  return output

def get_char_counts(word):
  output = {}
  for letter in list(word):
      #empty string is falsy
      if letter:
        try:
          output[letter] = output[letter] + 1
        except KeyError:
          output[letter] = 1
  return output
