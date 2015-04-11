# Bob

# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob:
  def hey(self, phrase):
    if PhraseTest.is_question(phrase):
      return 'Sure.'
    elif PhraseTest.is_shouting(phrase):
      return 'Woah, chill out!'
    elif PhraseTest.is_empty(phrase):
      return 'Fine. Be that way.'
    else:
      return 'Whatever.'

class PhraseTest:
  @classmethod
  def is_question(cls, phrase):
    trimmed_phrase = phrase.strip()
    return not(cls.is_empty(trimmed_phrase)) and trimmed_phrase[-1] == '?'
  
  @classmethod
  def is_shouting(cls, phrase):
    return not(cls.is_empty(phrase)) and phrase == phrase.upper()

  @classmethod
  def is_empty(cls, phrase):
    return len(phrase) == 0
