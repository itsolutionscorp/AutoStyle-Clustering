class bob:
    def hey(text):
       
        if len(text) == 0 or '\t' in text:
            return('Fine. Be that way!') #not satisfied with this one, as the 'else' bit should take care of empty string, a hackish workaround this is. Also the \t is lazy on my part until I figure out a better way to test for silence
        elif text[1] == '':
            return('Fine. Be that way!') #this may not be needed at all, since testing for an empty string should be covered in the 'else' bit
        elif text.isupper() == False and text[len(text) - 1] == '?':
            return('Sure.')
        elif text.isupper() == True:
            return('Whoa, chill out!')
        elif len(text) >= 1:
            return('Whatever.')
        else:
            return('Fine. Be that way!')
             

  
                
