from string import ascii_lowercase, punctuation

alphabet=[x for x in ascii_lowercase]

def prepare_to_encode(plaintext):
 return (''.join(plaintext.split())).lower()


def cipher_map(letter):
 if letter in alphabet:
  return alphabet[26-alphabet.index(letter)-1]
 if letter in punctuation:
  return ''
 else:
  return letter

def encode(plaintext):
 plain=[cipher_map(x) for x in prepare_to_encode(plaintext)]
 temp=''.join(plain)
 result=[]
 hop=5
 for index in range(0,len(temp),hop):
  result.append(temp[index:index+hop])
 return ' '.join(result)

def prepare_to_decode(ciphertext):
 return [x for x in ''.join(ciphertext.split())]


def decode(ciphertext):
 return ''.join(map(cipher_map,prepare_to_decode(ciphertext)))
