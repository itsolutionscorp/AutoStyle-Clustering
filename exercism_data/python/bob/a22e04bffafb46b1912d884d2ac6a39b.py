def has_letter(s):
    return any(i.isalpha() for i in s)

def hey(msg):
    if msg.strip() == '':
        return 'Fine. Be that way!'
    elif msg.endswith('?'):
        if msg == msg.upper() and has_letter(msg) == True:
            return 'Woah, chill out!'
        else:
            return 'Sure.'
    elif msg == msg.upper() and has_letter(msg) == True:
        return 'Woah, chill out!'
    else:
        return 'Whatever.'

print hey('4?')
