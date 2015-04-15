import string
import re
alpha = string.ascii_lowercase
omega = alpha[::-1]
alpha += '0123456789'
omega += '0123456789'
trans = string.maketrans(alpha,omega)


def encode(string):
  to_encode = re.sub('[^0-9a-z]+','',string.lower())
  temp = to_encode.translate(trans)
  counter = 0
  output = ''
  for letter in temp:
    if counter % 5 == 0 and counter != 0:
      output += ' '
    counter += 1
    output += letter
  return output


def decode(string):
  output = ''
  to_decode = string.replace(' ','')
  output = to_decode.translate(trans)
  return output
