# String methods: https://docs.python.org/release/2.5.2/lib/string-methods.html

def hey(what):
    #yelling
    if what.isupper():
        return 'Whoa, chill out!'
    
    #questions
    if what.endswith('?'):
        return 'Sure.' 
    
    #not saying anything
    if what.strip() == '':
        return 'Fine. Be that way!'
    
    #default
    return 'Whatever.'
