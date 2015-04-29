import string,re

alpha = string.ascii_lowercase
reverse = alpha[::-1]
alpha = alpha + '0123456789'
reverse = reverse + '0123456789'

def encode(phrase):
  encode_dict = dict(zip(alpha,reverse))
  code_phrase = ''.join([encode_dict[x] for x in phrase.lower().translate(string.maketrans("",""), string.punctuation).replace(' ','')])
  return re.sub(r'(.{5})(?=.)', r'\1 ',code_phrase)

  

  
def decode(phrase):
  decode_dict = dict(zip(reverse,alpha))
  return ''.join([decode_dict[x] for x in phrase.lower().translate(string.maketrans("",""), string.punctuation).replace(' ','')])
