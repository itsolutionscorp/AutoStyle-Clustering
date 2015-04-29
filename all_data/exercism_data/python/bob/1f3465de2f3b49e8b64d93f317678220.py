#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    
    if what == '':
        return 'Fine. Be that way!'
        
    capital_letters = map(lambda c: c.isupper(), filter(lambda c: c.isalpha(), what))
        
    if capital_letters and all(capital_letters):
        return 'Whoa, chill out!'    
    
    if what[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
