from string import maketrans

plain = 'abcdefghijklmnopqrstuvwxyz'
cipher = plain[::-1]
plain += plain.upper()
cipher += cipher
translation_table = maketrans(plain,cipher)

non_alphanumeric_chars = ''.join(c for c in map(chr, range(256)) if not c.isalnum())


def decode(text):
    return text.translate(translation_table).replace(" ","")

def encode(text):
    # Strip out non alpha-numeric characters
    cleansed_text = text.translate(None,non_alphanumeric_chars)
    # Seperate into array in blocks of 5 characters
    chunked_encoding = [cleansed_text.translate(translation_table)[i:i+5] for i in range(0,len(cleansed_text),5)]
    # return string with a space between each chunk
    return ' '.join(chunked_encoding)
