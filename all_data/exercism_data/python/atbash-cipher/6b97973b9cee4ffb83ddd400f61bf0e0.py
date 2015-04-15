alphabet = 'abcdefghijklmnopqrstuvxyz'
cipher = alphabet[::-1]
numbers = ['0','1','2','3','4','5','6','7','8','9']



def decode(string):
    cstring = string.replace(" ","")
    decoded = []
    for a in range(len(cstring)):
        if cstring[a] in numbers:
            decoded.append(cstring[a])
        else:
            decoded.append(alphabet[cipher.index(cstring[a])])
    return ''.join(decoded)
    
    
    
    
    
def encode(string):
    # lowercase it all, remove whitespace, punctuation, keep numbers
    cstring = string.lower().strip().replace(" ","").replace(",","").replace(".","")
    encoded = []
    for a in range(len(cstring)):
        if cstring[a] in numbers:
            encoded.append(cstring[a])
        else:
            encoded.append(cipher[alphabet.index(cstring[a])])
    i = 5        
    while i < (len(encoded) + len(encoded) / 5):
        encoded.insert(i," ")
        i += 6
    return ''.join(encoded)
    
        
        
def main():
    print encode(decode('abc def 123'))

if __name__ == "__main__":
    main()
