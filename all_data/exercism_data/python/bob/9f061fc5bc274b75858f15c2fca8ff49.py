import re
def hey( par ):

#silence expression
  if ( re.match( r'^\s*$', par ) ):
    return 'Fine. Be that way!' 

#yelling expressions
  #all caps with possible question mark	
  if ( re.match( r'^[A-Z\s]+\??$', par ) ):
    return 'Whoa, chill out!'
  #no lower case or lower case umlaut unicode codes within a range
  if ( re.match( ur'^[^a-z\u00E0-\u00ff]+[!]$', par ) ):
    return 'Whoa, chill out!'

#question expression
  if ( re.match( r'.+\?$', par ) ):
    return 'Sure.'

#all other options
  return 'Whatever.'
