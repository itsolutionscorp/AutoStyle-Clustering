def is_silence(test_str):
  return test_str.strip() == ''

def is_question(test_str):
  return test_str.strip()[-1] == '?'

def hey(what):
    if (what.isupper()): 
      return 'Whoa, chill out!'
    elif (is_silence(what)):
      return 'Fine. Be that way!'
    elif (is_question(what)):
      return 'Sure.'
    else: 
      return 'Whatever.'
