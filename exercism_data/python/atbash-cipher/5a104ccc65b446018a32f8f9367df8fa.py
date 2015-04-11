from string import maketrans

alphabet2 = ('a','b','c','d','e','f','g', 'h', 'i','j','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

def encode(plain_text):
    alphabet='abcdefghijklmnopqrstuvwxyz'
    
    #Lower all characters, remove spaces and punctuation
    encoded_text = plain_text.lower()
    encoded_text = encoded_text.replace(" ","")
    encoded_text = encoded_text.replace(".","")
    encoded_text = encoded_text.replace("!","")
    encoded_text = encoded_text.replace(",","")
    encoded_text = encoded_text.replace("?","")
    
    #Translate
    encoded_text = encoded_text.translate(maketrans(alphabet,alphabet[::-1]))
    
    #Put space after each five characters
    text_list = list(encoded_text)
    for i in list(reversed(range(5, len(encoded_text),5))):
        text_list.insert(i," ")
    encoded_text = ''.join(text_list)
        
    return encoded_text

def decode(encoded_text):
    alphabet='abcdefghijklmnopqrstuvwxyz'
    decoded_text = encoded_text.replace(" ","")
    return decoded_text.translate(maketrans(alphabet,alphabet[::-1]))
print decode("vcvix rhn")
print encode("no")
