import string

L = string.ascii_lowercase
D = string.digits

TRANS_TABLE = string.maketrans(L+D, L[::-1]+D)

def decode(msg):
  msg = msg.lower().replace(' ', '')
  return string.translate(msg, TRANS_TABLE, string.punctuation)

def encode(msg):
  msg = msg.lower().replace(' ', '')
  msg = string.translate(msg, TRANS_TABLE, string.punctuation)
  return " ".join([msg[i:i+5] for i in range(0, len(msg), 5)])
