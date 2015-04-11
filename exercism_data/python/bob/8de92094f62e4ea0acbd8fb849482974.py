# Bob

# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob:
  def hey(self, phrase_text):
    phrase = Phrase(phrase_text)
    if phrase.is_question():
      return 'Sure.'
    elif phrase.is_shouting():
      return 'Woah, chill out!'
    elif phrase.is_empty():
      return 'Fine. Be that way.'
    else:
      return 'Whatever.'

class Phrase:
  def __init__(self, phrase_text):
    self.phrase_text = phrase_text

  def is_question(self):
    trimmed_phrase = self.phrase_text.strip()
    return len(trimmed_phrase) > 0 and trimmed_phrase[-1] == '?'
  
  def is_shouting(self):
    return not(self.is_empty()) and self.phrase_text == self.phrase_text.upper()

  def is_empty(self):
    return len(self.phrase_text) == 0
