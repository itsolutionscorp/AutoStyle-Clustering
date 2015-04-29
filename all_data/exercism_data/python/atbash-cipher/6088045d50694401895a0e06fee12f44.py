def encode(string):
    translate_list = str.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba')
    string = string.replace(" ", "").replace(".", "").replace(",", "").lower()
    string = string.translate(translate_list)
    string = " ".join(string[i:i+5] for i in range(0, len(string), 5))
    return string

def decode(string):
    translate_list = str.maketrans('zyxwvutsrqponmlkjihgfedcba', 'abcdefghijklmnopqrstuvwxyz')
    string = string.replace(" ", "").lower()
    string = string.translate(translate_list)
    return string
