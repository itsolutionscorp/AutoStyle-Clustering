#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Default case
    retX = 'Whatever.'
    chill = 'Whoa, chill out!'
    sure = 'Sure.'
    fine = 'Fine. Be that way!'
    # Question that ends with ?
    if what.endswith('?'):
        retX = sure
    # YELL in all caps
    if what.isupper():
        retX = chill
    # Silence - starting with space
    if (what.startswith(' ') and what.endswith('\t')) or what == '':
        retX = fine
    
    return retX
