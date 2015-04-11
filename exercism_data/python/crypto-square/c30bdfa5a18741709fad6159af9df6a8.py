import math

def encode(word):
  punctuation = ['.', '!', '?', ',',
		 "'", '"', ';', ':',
		 ' ']
  word = word.lower()
  for p in punctuation:
    word = word.replace(p, '')
    
  squareRoot = math.sqrt(len(word))
  if squareRoot % 1 == 0:
    columns = squareRoot
  else:
    lenWord = len(word)
    while math.sqrt(lenWord) % 1 != 0:
      lenWord += 1
    columns = math.sqrt(lenWord)
  squareColumns = []
  for c in range(int(columns)):
    squareColumns.append([])
  pos = 0
  for l in word:
    if pos == columns:
      pos = 0
    squareColumns[pos].append(l)
    pos += 1
  code = ''
  for c in squareColumns:
    code += ''.join(c) + ' '
  return code.strip()

    
  
  
  
print encode('Run away.')
