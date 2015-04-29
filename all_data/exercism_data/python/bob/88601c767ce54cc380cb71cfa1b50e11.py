#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

## if what has a question mark in it and isn't all caps, its a question
  if what.rstrip().endswith("?")==True and what.isupper()==False: 
    reply = "Sure."
## if what is all upper case, bob is getting yelled at  
  elif what.isupper() == True:
    reply = "Whoa, chill out!"
## if what is null or has no character then bob is being addressed without you saying anything
  elif what.isspace()==True or not what:
    reply = "Fine. Be that way!"
  else:
    reply = "Whatever."
  return reply
