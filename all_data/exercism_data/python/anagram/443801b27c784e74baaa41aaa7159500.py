def is_anagram(word1, word2):
  word1 = word1.lower()
  word2 = word2.lower()
  if word1 == word2:
    return False
  length = len(word1)
  if length != len(word2):
    return False
  counts = dict((key.lower(), 0) for key in word1)
  for i in range(0, length):
    if word2[i] in counts:
      counts[word1[i]] += 1
      counts[word2[i]] -= 1
    else:
      return False
  return not(any(counts.values()))


def detect_anagrams(word, words):
  return [_w for _w in words if is_anagram(word, _w)]
