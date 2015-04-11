#
# Skeleton file for the Python "Bob" exercise.
#

import re
    
def hey(what):
    options = {'shout': 'Whoa, chill out!', 
               'statement': 'Whatever.',
               'silence': 'Fine. Be that way!',
               'question': 'Sure.'}
      
    response = identify_statement(what)
    return options.get(response)

def identify_statement(what):
    if not what or re.match('\W+[\t]+', what):
         return 'silence'
    
    if what.upper() == what and len(what) > 0 and re.search('[A-Za-z]', what):
        return 'shout'

    if what.strip().endswith('?'):
        return 'question'
    
    return 'statement'
