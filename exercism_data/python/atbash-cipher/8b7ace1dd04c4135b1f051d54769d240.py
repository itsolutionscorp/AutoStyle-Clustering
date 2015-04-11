import string
encode_dict = {char:string.lowercase[25-i] for (i,char) in enumerate(string.lowercase)}
for (i,char) in enumerate(string.uppercase):
    encode_dict[char] = string.lowercase[25 - i]
for i in range(0,10):
    encode_dict[str(i)] = str(i)
decode_dict = {value:key for key,value in encode_dict.iteritems()}

def chunkstring(string, length):
    return ' '.join([string[0+i:length+i] for i in range(0, len(string), length)])

def encode(input_str):
    input_str = ''.join(ch for ch in input_str if ch.isalnum())
    return chunkstring(''.join([encode_dict[c] for c in input_str]), 5)

def decode(input_str):
    return ''.join([encode_dict[c] for c in input_str.replace(' ','')])
