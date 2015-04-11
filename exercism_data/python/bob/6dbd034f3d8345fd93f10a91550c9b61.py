#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  
  bob_response = "Whatever."
    
  if what.endswith("?"):
    bob_response = "Sure."
    
  if what.isupper():
    bob_response = "Whoa, chill out!"
  
  if what == "" or what.isspace():
    bob_response = "Fine. Be that way!"
  
  return bob_response
  
  
