def word_count(string):
  string = string.replace('\n', ' ')
  werdz = {}
  cursor = 1
  anchor = 0
  while cursor < len(string):
    if string[cursor] == " " and string[cursor-1] == " ":
      anchor += 1
    elif cursor + 1 == len(string):
      word = string[anchor:len(string)]
      if word in werdz:
        werdz[word] += 1
      else:
        werdz[word] = 1
    elif string[cursor] == " ":  
      word = string[anchor:cursor]
      if word in werdz:
        werdz[word] += 1
      else:
        werdz[word] = 1
      anchor = cursor + 1 
    cursor += 1
  return werdz
