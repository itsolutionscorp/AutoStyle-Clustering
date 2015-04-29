#
# Skeleton file for the Python "Bob" exercise.
#

import string

class Bob:
    
    def hey(self, hey):

        hey2 = hey.replace(",","")
        hey2= hey2.replace(" ","")
         
        if hey2.isdigit() == True:
            print hey
            print "Whatever."
            return "Whatever."
            
        
        elif hey.isupper() == True:
            print hey
            print "Woah, chill out!"
            return "Woah, chill out!"

        elif hey.endswith(".") == True:
            print hey
            print "Whatever."
            return "Whatever."
                
        elif hey.endswith("?") == True:
            print hey
            print "Sure."
            return "Sure."
            
        elif hey.endswith("!") == True:
            print hey
            print "Whatever."
            return "Whatever."

        elif not hey:
            print "Fine. Be that way!"
            return "Fine. Be that way!"

        else:
            print "Fine. Be that way!"
            return "Fine. Be that way!"


        
