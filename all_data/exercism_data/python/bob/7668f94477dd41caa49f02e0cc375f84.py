#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    what = what.rstrip()
    
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif containsLetters(what) == False:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

    return ''



def containsLetters(to_search):
    ch = 'abcdefghijklmnopqrstuvwxyz0123456789';
    ch_len = len(ch)
    
    """ First Check if the string contains anything a to z"""
    """ Then Check if the string contains anything from 0 to 9 
    If any of them returns a true return true else False
    """
    
    to_search = to_search.lower()
    searching = True
    counter = 0
    
    while searching:
        if to_search.find(ch[counter]) != -1:
            return True
        if counter < (ch_len-1):
            counter += 1
        else:
            searching = False
            break
    
    searching = True
    
    return False
