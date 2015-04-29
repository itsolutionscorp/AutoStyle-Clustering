#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):

    response_dict = {1: 'Sure.',
                     2: 'Whoa, chill out!',
                     3: 'Fine. Be that way!',
                     4: 'Whatever.'}

    n = 4
    
    what = what.strip()
    
    nothing_said = (not what)
    asked = what.endswith('?')
    
    char_only = "".join(re.findall("[a-zA-Z]+", what))
    screaming = char_only and all(letter.isupper() for letter in char_only)
    
    if nothing_said:
        n = 3
    elif asked and not screaming:
        n = 1
    elif screaming:
        n = 2
    
    response = response_dict[n]
    
    return response
