#
# Skeleton file for the Python "Bob" exercise.
#
import re
from abc import ABCMeta, abstractmethod

class Analyzer:
	''' 
	Abstract class for text analyzers. 
	Contains general setup, common methods for all derived classes and abstract method definition
	'''
	__metaclass__ = ABCMeta
	answer = 'Whatever.'
	
	def __init__(self, text):
		#before assigning the text, clean it up!
		self.text = self.filterString(text)
		
	def filterString(self, text):
		#expand tabs into spaces and get rid of trailing spaces
		return text.expandtabs().strip()
		
	def getAnswer(self):
		#return class' defined answer
		return self.answer

	@abstractmethod
	def analyze(self): pass

class IsShouting(Analyzer):
	'''
	Determines if the text is of a shouting sentence
	'''
	p = re.compile(ur'[A-Za-z]+')
	answer = 'Whoa, chill out!'

	def analyze(self):
		return self.text == self.text.upper() and self.hasAlphaChars()
	
	def hasAlphaChars(self):
		#determines if the analyzed string has alpha characters
		matchObj = re.search(self.p, self.text)
		if matchObj:
			return True
		else:
			return False

class IsAsking(Analyzer):
	'''
	Determines if the text is of an asking sentence
	'''
	answer = 'Sure.'
	
	def analyze(self):
		shoutAnalyzer = IsShouting(self.text)
		return self.text.strip().endswith('?') and not shoutAnalyzer.analyze()
		
class IsSilence(Analyzer):
	'''
	Determines if the text has no text at all
	'''
	answer = 'Fine. Be that way!'
	
	def analyze(self):
		return not self.text
			
def hey(what):
	analyzers = [IsSilence(what), IsAsking(what), IsShouting(what)]
	for analyzer in analyzers:
		if analyzer.analyze():
			return analyzer.answer
	return Analyzer.answer
	
