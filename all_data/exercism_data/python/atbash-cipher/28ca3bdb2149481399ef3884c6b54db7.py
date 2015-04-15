def decode(message):
    if type(message)!=type(""):
        return "invalid string"
    message=message.lower()
    cipher={"a":"z","b":"y","c":"x","d":"w","e":"v","f":"u","g":"t","h":"s","i":"r","j":"q","k":"p","l":"o","m":"n","n":"m","o":"l","p":"k","q":"j","r":"i","s":"h","t":"g","u":"f","v":"e","w":"d","x":"c","y":"b","z":"a","1":"1","2":"2","3":"3","4":"4","5":"5","6":"6","7":"7","8":"8","9":"9","0":"0"}
    return_list=[]
    counter=5
    for letter in message:
        try:
            return_list.append(cipher[letter])
            counter=counter-1
        except:
            pass

        if counter==0 and len(return_list)!=len(message):
            return_list.append(" ")
            counter=5
    return "".join(return_list)

def encode(message):
    return decode(message)
