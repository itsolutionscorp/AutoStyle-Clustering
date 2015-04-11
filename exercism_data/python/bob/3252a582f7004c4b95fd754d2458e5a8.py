#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    print(what)
    #question
    if (('\''in what) or (u'\xe4' in what) == True):
      return("Whatever.")
    elif ((what.endswith("!")) or (what.isupper())):   
        return("Whoa, chill out!")
    #---if line ends with a exclamation mark or is all caps
    elif what.endswith("?"):
        return("Sure.")
    #---umlaut or  apostrophe
      #char is not digit or letter
    elif ((any(char.isalpha() for char in what) or (any(char.isdigit() for char in what)))== False):
        return("Fine. Be that way!")
    #anything else
    else:
        return("Whatever.")
    
