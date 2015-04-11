import re

def hey(input):
    
    endsinq = re.search("(\?$)",input)
    allblank = re.search("(^\s*$)",input)
    endsinex = re.search("(!$)",input)
    containsupper = re.search("([A-Z])",input)
    allupper = input.upper() == input;

    if allblank:
        return 'Fine. Be that way!'
    elif containsupper and allupper:  #Contains letters and they're all ucase
        return 'Whoa, chill out!'
    elif endsinq:                     #Ends in ? but have now elminated ucase
        return 'Sure.'
    else:
        return 'Whatever.'
