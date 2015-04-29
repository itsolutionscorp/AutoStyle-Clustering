import string

# Create plaintext and cipher (reversed) alphabets
ALPHABET = string.ascii_lowercase
ATBASH = ALPHABET[::-1]


def decode(s):
    s = s.replace(" ", "")
    decoded = ''

    # Match indexes of alphabets. If not found, use the original character
    for ch in s:
        try:
            decoded = decoded + ALPHABET[ATBASH.index(ch)]
        except:
            decoded = decoded + ch
    return(decoded)

def encode(s):
    
    # Remove whitespace and punctuation, change to lowercase
    exclude = set(string.punctuation + " ")
    s = ''.join(ch for ch in s if ch not in exclude)
    s = s.lower()
    
    encoded = ""
    counter = 0
    for ch in s:
        
        # Add space after every 5 characters
        if counter == 5:
            encoded += " "
            counter = 0
            
        # Match indexes of alphabets. If not found, use the original character
        try:
            encoded = encoded + ATBASH[ALPHABET.index(ch)]
        except:
            encoded = encoded + ch
        counter += 1
        
    return(encoded)
            
