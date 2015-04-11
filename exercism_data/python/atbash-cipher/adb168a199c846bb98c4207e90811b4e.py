cipher = {'1':'1', '2':'2', '3':'3', 'a':'z','b':'y','c':'x','d':'w','e':'v','f':'u','g':'t','h':'s','i':'r','j':'q','k':'p','l':'o','m':'n','n':'m','o':'l','p':'k','q':'j','r':'i','s':'h','t':'g','u':'f','v':'e','w':'d','x':'c','y':'b','z':'a'}
def encode(sequence):
    encoded = ''.join([cipher[character] for character in sequence.lower() if character not in [' ',',','.']])
    segment_length=5
    return ' '.join([encoded[i:i+segment_length] for i in range(0, len(encoded), segment_length)])

def decode(sequence):
    return ''.join([cipher[character] for character in sequence.lower() if character in cipher])

