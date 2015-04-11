import string

def hey(say):
    if len(say) < 1 or len(say.strip()) < 1:
        return 'Fine. Be that way!'
    elif allnums(say):
        if '?' in say:
            return 'Sure.'
        else:
            return 'Whatever.'
    elif  allcaps(say):
        return 'Whoa, chill out!'
    elif '?' in say[-1]:
        return 'Sure.'
    elif '?' in say[0:-2] :
        return 'Whatever.'
    elif say.endswith('\!') :
        return 'Whoa, chill out!'
    elif ' ' in say[0]:
        return 'Whatever.'
    elif allcaps(say[0:-2]):
        return 'Whoa, chill out!'
    else :
        return 'Whatever.'

def allnums(said):
    numNo = 0
    said = said.replace(' ', '')
    #said = said.translate(None, string.punctuation);

    for j in string.punctuation:
        said = said.replace(j,'')
    for i in said.strip():
        if not i.isdigit():
            return False
        else:
            numNo+=1
        if numNo == len(said.strip()):
            return True


def allcaps(said):
# number of capitals
    capNo = 0
    for i in said.strip():
# if lower case
        if i.islower():
            return False
        else:
            capNo+=1
    if capNo == len(said.strip()):
        return True
