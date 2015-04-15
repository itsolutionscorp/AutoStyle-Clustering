# Build dictionary containing acceptable characters.
cipher = {'1':'1', '2':'2', '3':'3', 'a':'z','b':'y','c':'x','d':'w','e':'v','f':'u','g':'t','h':'s','i':'r','j':'q','k':'p','l':'o','m':'n','n':'m','o':'l','p':'k','q':'j','r':'i','s':'h','t':'g','u':'f','v':'e','w':'d','x':'c','y':'b','z':'a'}

def encode(sequence):

    # Make a string containing only the alphanumeric characters.
    encoded = ''.join([cipher[character] for character in sequence.lower() if character not in [' ',',','.']])

    # Place a space character after every 5th character.
    segment_length=5
    encoded_sequence = ' '.join([encoded[i:i+segment_length] for i in range(0, len(encoded), segment_length)])

    # Return the string.
    return encoded_sequence

def decode(sequence):
    decoded_sequence = ''.join([cipher[character] for character in sequence.lower() if character in cipher])
    return decoded_sequence

