alphabet = "abcdefghijklmnopqrstuvwxyz"
encryption = {alphabet[ind]:alphabet[-(ind+1)] for ind,letter in enumerate(alphabet)}

#[line[i:i+n] for i in range(0, len(line), n)]

def encode(words):
    coded = "".join(map(lambda x:clean(x), words.lower()))
    return " ".join([coded[i:i+5] for i in range(0, len(coded), 5)])

def decode(words):
    return "".join(map(lambda x: encryption[x], words.replace(" ", "")))

def clean(charc):
    if charc in alphabet:
        return encryption[charc]
    elif charc.isalnum():
        return charc
    else:
        return ""
