import string
import math

def encode(msg):

  msg = filter(str.isalnum, msg).replace(" ","").lower()

  if (len(msg) == 0):
    return ""
  
  num_columns = int(math.sqrt(len(msg)))
  if num_columns * num_columns < len(msg):
    num_columns += 1 

  num_rows = int(len(msg)/num_columns)
  if num_columns * num_rows < len(msg):
    num_rows += 1 

  plain = []
  for i in range(num_rows):
    plain.append(msg[:num_columns])
    if (len(plain[-1]) < num_columns):
      plain[-1] = plain[-1] + (" " * (num_columns - len(plain[-1])))
    msg = msg[num_columns:]
  
  return " ".join([ "".join(t).strip() for t in zip(*plain) ])
