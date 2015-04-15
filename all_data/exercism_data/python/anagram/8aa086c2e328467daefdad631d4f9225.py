def detect_anagrams(target, words):
  output = []
  target = target.lower()
  target_letter_counts = get_char_counts(target)
  for item in words:
    word = item.lower()
    if target_letter_counts == get_char_counts(word) and set(word) == set(target) and target not in word:
      output.append(item)
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
