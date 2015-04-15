import string

alphabet = string.ascii_letters[0:26]
cipher = string.maketrans(alphabet,alphabet[::-1])


def decode(txt):
    return string.translate(txt, cipher, deletions='" "')
    
def encode(txt):
    encoded = string.translate(string.lower(txt), cipher, deletions='" ",",","."')
    encoded_and_spaced = []
    numspaces = len(encoded) / 5

    i = 0
    while i < numspaces+1:
        encoded_and_spaced += encoded[i*5:(i*5)+5]
        encoded_and_spaced += " "
        i += 1
    return string.strip(''.join((encoded_and_spaced)))
