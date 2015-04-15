import string

def encode(message):
    table = string.maketrans(string.lowercase,string.lowercase[::-1])
    coded = message.lower().translate(table,string.punctuation).replace(' ','')
    #needed to add the rstrip() to remove potential trailing spaces. Do you ahve a better idea?
    #also is there a better way to partition this?
    return " ".join(coded[5*i:5*i+5] for i in range(len(coded)/5+1)).rstrip()

def decode(message):
    return encode(message).replace(' ','')
