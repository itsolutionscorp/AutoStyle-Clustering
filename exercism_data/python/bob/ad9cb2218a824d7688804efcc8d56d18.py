# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.
import re

def hey(sentence):
	
	saidSomething = re.search(r'\S+', sentence)
	
	if saidSomething:
		wordsMatched = re.findall(r'[a-zA-Z]+', sentence) 
		words = wordsMatched if wordsMatched else ["a", "a"]
		saidForcefully = sentence.isupper()
		# print "%s is %s " % (words, saidForcefully)
		
		if saidForcefully or sentence.endswith('!'):
			return 'Whoa, chill out!'
		elif sentence.endswith('?') and not saidForcefully:
			return 'Sure.'
		else:		
			return 'Whatever.'
	else:
		return 'Fine. Be that way!'
