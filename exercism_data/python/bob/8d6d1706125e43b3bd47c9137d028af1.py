def hey(string):
    if string.strip() == '':
        return 'Fine. Be that way!'
    elif isAllCaps(string):
        return 'Whoa, chill out!'
    elif string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

def isAllCaps(string):
    if isOnlyNumbers(string):
        return False
    
    for letter in string:
        if letter.upper() != letter:
            return False
    return True

punctuation='!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~ '
digits='0123456789'
def isOnlyNumbers(string):
    num_of_letters = 0
    for letter in string:
        if letter not in digits and letter not in punctuation:
            num_of_letters += 1
    return num_of_letters == 0
