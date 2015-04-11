#
# IDiggity's solution to the Python "Bob" exercise.
#
def hey(what): #Defines how "Bob" responds
    
    if what.strip()=='':
        return 'Fine. Be that way!'
    elif what.isupper(): #String where all letters are uppercase, i.e. yelling
        return 'Whoa, chill out!'
    elif what.endswith('?'): #String that isn't empty, isn't yelling, but ends with ?, i.e. question
        return 'Sure.'    
    else: #String that isn't empty, whitespace, yelling, or a question
        return 'Whatever.'  
    
    
