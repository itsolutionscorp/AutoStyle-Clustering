#bob module
import re 


def hey(toBob):
    if toBob.isupper() :
        return 'Whoa, chill out!'
    elif re.search(r'\?$',toBob):
        return "Sure."
    elif toBob.strip() == '' :
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
