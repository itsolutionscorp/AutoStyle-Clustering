import re
def hey( par ):

  #silence - reply 'Fine. Be that way!'
  #prolonged silence - reply 'Fine. Be that way!'
  if ( re.match( r'^\s*$', par ) ):
    return 'Fine. Be that way!' 

  #shouting special characters - reply 'Whoa, chill out!'
  #shouting with umlauts - reply 'Whoa, chill out!'
  #shouting with no '!' - reply 'Whoa, chill out!'
  if ( re.match( r'^[A-Z\s]+$', par ) ):
    return 'Whoa, chill out!'
  #shouting - reply 'Whoa, chill out!'
  #forceful question (capitals ending in '?' - reply 'Whoa, chill out!'
  if ( re.match( r'^[A-Z\s]+[?]$', par ) ):
    return 'Whoa, chill out!'
  #shouting numbers (numbers and capitals ending in '!' - reply 'Whoa, chill out!'
  if ( re.match( ur'^[^a-z\u00E4]+[!]$', par ) ):
    return 'Whoa, chill out!'


  #prattling on (ending in '?' with '!' in middle) - reply 'Sure'
  #question with only numbers - reply 'Sure'
  #asking a numeric question - reply 'Sure'
  #asking a question - reply 'Sure'
  if ( re.match( r'^[\w\s\d,\.\!]+[?]$', par ) ):
    return 'Sure.'




  #starts with whitespace - reply 'Whatever'
  #stating something - reply with 'Whatever'
  #statement with '?' in middle - reply 'Whatever'
  #calmy speaking umlauts - reply 'Whatever'
  #only numbers - reply 'Whatever'
  #using acronyms in regular speech - reply 'Whatever'
  #talking forcefully (lowercase ending in '!')- reply 'Whatever'
  return 'Whatever.'
