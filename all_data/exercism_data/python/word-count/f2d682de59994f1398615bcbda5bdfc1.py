def word_count(phrase):
    words = phrase.split()

    result = {}
    for word in words:
      if not word in result:
        result.setdefault(word)
        result[word] = 1
      else:
        result[word] += 1

    return result
