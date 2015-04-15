import string

alphabet = string.lowercase
cipher = {}

for i in xrange(len(alphabet)):
  cipher[alphabet[i]] = alphabet[(i+1)*-1]
  cipher[alphabet[(i+1)*-1]] = alphabet[i]

def transpose(s):
  outputS = ''
  s = s.lower()
  for i in s:
    if i in cipher:
      outputS += cipher[i]
    elif i.isalnum():
      outputS += i
  return outputS

def decode(s):
  return transpose(s)

def encode(s):
  s = transpose(s)
  outputS = ''
  for i in xrange(len(s)):
    if i % 5 == 0 and i != 0:
      outputS += ' ' + s[i]
    else:
      outputS += s[i]
  return outputS
