__author__ = 'jeffmarkey'

def encode(text):
    plain = 'abcdefghijklmnopqrstuvwxyz1234567890 '
    cipher = 'zyxwvutsrqponmlkjihgfedcba1234567890 '

    encode_map = {}
    for key, value in zip(plain, cipher):
        encode_map[key] = value

    new_text = []

    text = clean(text)
    for char in text:
        try:
            new_text.append(encode_map[char])
        except:
            pass

    final_text = []
    counter = 0

    for char in new_text:
        counter = counter + 1
        final_text.append(char)
        if (counter % 5 == 0):
            final_text.append(' ')

    if(counter % 5 == 0):
        del final_text[-1]

    return ''.join(final_text)

def decode(text):
    plain = 'abcdefghijklmnopqrstuvwxyz1234567890'
    cipher = 'zyxwvutsrqponmlkjihgfedcba1234567890'

    decode_map = {}
    for key, value in zip(cipher, plain):
        decode_map[key] = value

    text = clean(text)
    new_text = []
    for char in text:
        try:
            new_text.append(decode_map[char])
        except:
            pass

    return ''.join(new_text)

def clean(text):
    replace_list = ['!',':','&','%','^',',','@','$', '_','.',' ']

    if (' ' in str(text)):
        add_spaces = True

    for line in replace_list:
        text = text.replace(line,'')
    text = text.lower()

    chars = []
    for elm in text:
        chars.append(elm)

    len(chars)

    return text
