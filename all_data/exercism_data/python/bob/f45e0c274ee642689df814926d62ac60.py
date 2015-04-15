#
# Skeleton file for the Python "Bob" exercise.
# he returns basic answers to user input
import re

def hey(what):
    
    #if screaming
    if((what.find('!') !=-1 and what.isupper()) or what.isupper()):
        return 'Whoa, chill out!'  
   
    #if a question
    elif(what.find('?') !=-1):
        temp=re.sub(' ','',what)  #to get rid of spaces
        if(temp.find('?') == (len(temp)-1)):
            return 'Sure.'
        else:
            return 'Whatever.'    
     
    #if you adress him without saying anything
    elif(what.isspace() or not what):
        return 'Fine. Be that way!' 

    #anything else
    else:
        return 'Whatever.'
    
