def hey(sentence):
  if does_not_say_anything(sentence): return 'Fine. Be that way!'
  if is_a_question(sentence): return 'Sure.'
  if is_yelling(sentence): return 'Woah, chill out!'
  return 'Whatever.'

def does_not_say_anything(sentence):
  return sentence.strip() == ""

def is_a_question(sentence):
  return sentence and not is_yelling(sentence) and sentence[-1] == "?"

def is_yelling(sentence):
  return sentence.isupper()
