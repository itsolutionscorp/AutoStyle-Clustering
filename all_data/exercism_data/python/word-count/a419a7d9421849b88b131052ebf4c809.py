def word_count(s):
  return {word:s.split().count(word) for word in set(s.split())}
