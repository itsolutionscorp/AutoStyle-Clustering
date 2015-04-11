#
# Skeleton file for the Python "Bob" exercise.
#
def hey(phrase):
  if phrase.isspace() == True or phrase == '':#True: # and phrase.isalpha() == False:
    return 'Fine. Be that way!'
  
  elif phrase.isupper():
    return 'Whoa, chill out!'
  
  elif phrase[-1] == '?': 
  #elif phrase[-1] == '?':
    return 'Sure.'
  
  #elif phrase.isupper():
    #return 'Whoa, chill out!'
  
  else:
   #phrase[-1] != '?' and phrase[-1] != '!':
    return 'Whatever.'
