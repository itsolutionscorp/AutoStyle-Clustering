class Bob(object):
  @classmethod
  def is_empty(klass,word):
  	return word == None or len(word.strip()) == 0

  def _is_silent(self,word):
  	return Bob.is_empty(word)

  def _is_shouted_at(self,word):
  	return word == word.upper()

  def _is_questioned(self,word):
  	return word[-1] == '?'

  def _is_rudely_spoken_to(self,word):
  	return word[-1] == '!'

  def _is_spoken_to(self,word):
  	return word[-1] == '.'

  def hey(self,word):
  	result = ''
  	if self._is_silent(word):
  		result = 'Fine. Be that way!'
  	else:
			if self._is_shouted_at(word):
			    result = 'Woah, chill out!'
			elif self._is_questioned(word):
			    result = 'Sure.'
			elif self._is_rudely_spoken_to(word) or self._is_spoken_to(word):
			    result = 'Whatever.'
	return result
