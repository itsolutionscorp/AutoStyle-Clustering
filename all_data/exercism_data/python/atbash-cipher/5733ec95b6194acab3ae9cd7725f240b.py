from string import ascii_lowercase, punctuation

key = dict(zip(list(ascii_lowercase), list(ascii_lowercase[::-1])))
key.update(zip(map(str, range(10)), map(str, range(10))))

def decode(code):
    text = ''

    for letter in code.replace(' ', ''):
        text += key[letter]

    return text
    
def encode(text):
    code = ''
    
    for letter in format_text(text):
        code += key[letter]

    # Split the code into groups of 5.
    split_code = [code[i:i+5] for i in range(0,len(code),5)] 

    return ' '.join(split_code)

def format_text(text):
    # Convert to lowercase, remove spaces and punctuation.
    text = text.lower()
    text = text.replace(' ', '')
    for p in punctuation:
        text = text.replace(p, '')

    return text
