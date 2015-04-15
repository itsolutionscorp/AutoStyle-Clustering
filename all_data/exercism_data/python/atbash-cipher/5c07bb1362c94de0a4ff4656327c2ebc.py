import string

#---------create static dictionary with code answer--------
atbash_cipher = {a_to_z:z_to_a for a_to_z,z_to_a in zip(
                                    string.ascii_lowercase,
                                    reversed(string.ascii_lowercase))}

#-----------------------code-------------------------------
def encode(secret):

    #remove spaces + punctuation, make lowercase
    secret = secret.replace(' ', '')
    for char in string.punctuation:
        secret = secret.replace(char, '')
    secret = secret.lower()  

    space_counter = 0
    code = ''

    #change letter by letter adding spaces every 5 chars
    for char in secret:
        if space_counter % 5 == 0 and space_counter > 0:
            code += ' '
        if char.isdigit():
            code += char
        else:
            code += atbash_cipher[char]
        space_counter += 1
    
    return code 

def decode(code):
    code = code.replace(' ', '')
    
    secret = ''

    for char in code:
        secret += atbash_cipher[char]

    return secret
