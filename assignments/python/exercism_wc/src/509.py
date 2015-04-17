def word_count(phrase):
  response = {}
  for word in phrase.split():
    if    word in response: response[word] += 1
    else: response[word] = 1
  return response
