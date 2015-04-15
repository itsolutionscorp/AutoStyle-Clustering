import re

def hey(what):
    if(     what.upper() == what
        and what != ""
        and re.search('[a-zA-Z]', what)
       ):
        return('Whoa, chill out!')

    elif(   what.endswith("?")
         or what.endswith(" ")):
        return('Sure.')

    elif(what.endswith("\t") or what ==""):
        return('Fine. Be that way!')

    else:
        return('Whatever.')
