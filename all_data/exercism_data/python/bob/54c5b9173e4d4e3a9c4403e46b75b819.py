import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    stripped_what = noNumbers(what.strip())
    
    if len(stripped_what) == 0:
        return "Fine. Be that way!"
    elif shouting(stripped_what):
        return "Whoa, chill out!"        
    elif question(stripped_what):
        return "Sure."

    return "Whatever."

def shouting(what):
    non_numbers = re.sub(r'([\d])+','',what)
    what_upcase = what.upper()
    return (what == what_upcase) \
        and len(non_numbers) != 0 \
        and non_numbers != '?'

def question(what):
    return what[-1] == '?'

def noNumbers(what):
    return re.sub(r'[^\u00BF-\u1FFF\u2C00-\uD7FF\w]','',what)
