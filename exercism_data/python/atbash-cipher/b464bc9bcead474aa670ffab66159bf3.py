from string import maketrans,punctuation
from re import sub

abc = "abcdefghijklmnopqrstuvwxyz"
trans_t = maketrans(abc, abc[::-1])
#pattern to match either a punctuation char or an underscore or a space char.
punc_space_regex = "[" + "".join(punctuation) + "_\s]"

def encode(message):
    #get rid of grabage chars
    tmp = sub(punc_space_regex, "", message.lower().translate(trans_t))
    code =""
    #split code into sequential groups of 5 seperated by a space
    for i in range(0, len(tmp)):
        if i % 5 == 0 and i:
            code += " "
        code += tmp[i]
    return code

def decode(code):
    return sub(punc_space_regex, "", code.lower().translate(trans_t))
