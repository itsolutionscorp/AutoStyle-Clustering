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
    return trimmed_phrase.endswith('?')
  
  def is_shouting(self):
    return self.phrase_text.isupper()

  def is_empty(self):
    return not self.phrase_text
