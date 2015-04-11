#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Deal with umlat characters
    new = what.decode('utf-8', 'ignore')

    # If is upper case
    if new.isupper():
        return 'Whoa, chill out!'

    #If contains questionmark at end
    elif "?" in new[-1:]:
        return 'Sure.' 

    # Strips out blank characters
    elif not new.strip():
        return 'Fine. Be that way!'

    # If doesnt fit other cases
    else: 
        return 'Whatever.'
    
