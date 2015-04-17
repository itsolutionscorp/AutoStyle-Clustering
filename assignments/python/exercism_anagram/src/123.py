def detect_anagrams(source, wordlist):
  """detect_anagrams
  detect_anagrams takes a source word and searchs a given list
  for any anagrams in that list, returning a list of anagrams
  Args:
      :param source: the string which we are searching for anagrams for
      :param wordlist: a list of strings that are potential anagrams
  Returns:
      A list of anagrams, which may be empty
  """
  src = list(source.lower())
  words = list()
  for word in wordlist:
    s = list(src)
    w = list(word.lower())
    for ch in src:
      if ch in w:
        w.remove(ch)
        s.remove(ch)
    if(len(w) == 0 and len(s) == 0 and
       source.lower() != word.lower()):
        words.append(word)
  return words
