import regex

# Unicode Handling
#   * http://stackoverflow.com/questions/14539807/convert-unicode-with-utf-8-string-as-content-to-str

def hey(text):
  question = regex.compile('\?$')
  gentle_address = regex.compile(ur'[\p{Ll}]+')
  forceful_question = regex.compile(ur'[\p{Lu}]+\s*[\?\!]?')
  prolonged_silence = regex.compile('^\s*$')

  text = text.encode('utf-8').decode('utf-8')

  if ( prolonged_silence.search(text) != None ):
    return 'Fine. Be that way!'
  elif ( forceful_question.search(text) != None and gentle_address.search(text) == None ):
    return 'Whoa, chill out!'
  elif ( question.search(text) != None ):
    return 'Sure.'
  else:
    return 'Whatever.'
