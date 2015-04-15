import re
from string import maketrans

def atbash(inp):
    plain = "abcdefghijklmnopqrstuvwxyz"
    translation_table = maketrans(plain, plain[::-1]) 
    return clean(inp).translate(translation_table)
    
def clean(inp):
    pattern = re.compile('[\W_]+')
    return pattern.sub('', inp.lower())

def encode(plaintext):
    return group(atbash(plaintext))

def group(ciphertext):
    GROUP_LENGTH = 5
    grouped_text = [ciphertext[i : i+GROUP_LENGTH] 
                    for i in range(0, len(ciphertext), GROUP_LENGTH)]
    return ' '.join(grouped_text)    

def decode(ciphertext):
    return atbash(ciphertext)
