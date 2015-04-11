class Bob:
  def hey(self, sentence):
    if self.__is_silent(sentence):
      return 'Fine. Be that way!'

    if self.__is_yelling(sentence):
      return 'Woah, chill out!'

    if self.__is_asking(sentence): 
      return 'Sure.'

    return 'Whatever.'

  def __is_yelling(self, sentence):
    return sentence.isupper()

  def __is_silent(self, sentence):
    return sentence.isspace() or sentence == ''

  def __is_asking(self, sentence):
    return sentence.endswith('?')
