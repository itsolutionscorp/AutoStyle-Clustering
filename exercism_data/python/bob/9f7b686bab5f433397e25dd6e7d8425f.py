import re
def hey( par ):

#silence expression
  if ( re.match( r'^\s*$', par ) ):
    return 'Fine. Be that way!' 

#yelling expressions
  #all caps 
  if ( re.match( r'^[A-Z\s]+$', par ) ):
    return 'Whoa, chill out!'
  #all caps and question
  if ( re.match( r'^[A-Z\s]+[?]$', par ) ):
    return 'Whoa, chill out!'
  #no lower case
  if ( re.match( ur'^[^a-z\u00E4]+[!]$', par ) ):
    return 'Whoa, chill out!'

#question expression
  if ( re.match( r'^[\w\s\d,\.\!]+[?]$', par ) ):
    return 'Sure.'

#all other options
  return 'Whatever.'
