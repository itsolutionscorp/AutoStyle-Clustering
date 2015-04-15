def hey(sentence):
  if _does_not_say_anything(sentence): return 'Fine. Be that way!'
  if _is_a_question(sentence): return 'Sure.'
  if _is_yelling(sentence): return 'Woah, chill out!'
  return 'Whatever.'

def _does_not_say_anything(sentence):
  return sentence.strip() == ""

def _is_a_question(sentence):
  return sentence and not _is_yelling(sentence) and sentence[-1] == "?"

def _is_yelling(sentence):
  return sentence.isupper()
