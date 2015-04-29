import string

def hey(text):
    contains = False
    for c in (string.ascii_letters + string.digits):
        if c in text:
            contains = True

        if contains == True:
            break;
    
    if contains == False:
        return u'Fine. Be that way!'

    
    if text.isupper():
        return u'Whoa, chill out!'

    if text.endswith('?'):
        return u'Sure.'

    else:
        return u'Whatever.'
