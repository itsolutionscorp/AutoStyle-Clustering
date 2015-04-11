import string

def encode(text):
    
    def turn_lowercase_and_remove_spaces(t):
        return t.lower().replace(' ', '')

    def remove_punctuation(t):
        t = t.replace(',', '')
        t = t.replace('.', '')
        return t

    def run_cipher_transform(t):
        translation_table = str.maketrans(
            string.ascii_lowercase, string.ascii_lowercase[::-1])
        
        return t.translate(translation_table)

    def add_space_after_every_5th_char(t):
        i = 1
        new_text = ''

        for letter in t:
            if i == 5:
                new_text += (letter + ' ')
                i = 1
            else:
                new_text += letter
                i += 1
        return new_text.rstrip(' ')

    

    text = turn_lowercase_and_remove_spaces(text)
    text = remove_punctuation(text)
    text = run_cipher_transform(text)
    text = add_space_after_every_5th_char(text)

    return text


def decode(text):
    
    def remove_spaces(t):
        return t.replace(' ', '')

    def reverse_cipher(t):
        translation_table = str.maketrans(
             string.ascii_lowercase[::-1], string.ascii_lowercase)
        return t.translate(translation_table)

    text = remove_spaces(text)
    text = reverse_cipher(text)

    return text
