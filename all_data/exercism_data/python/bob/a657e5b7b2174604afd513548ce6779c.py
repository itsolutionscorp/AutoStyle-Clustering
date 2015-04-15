#Bob is a lackadaisical teenager. In conversation, his responses are very limited.
#Bob answers 'Sure.' if you ask him a question.
#He answers 'Whoa, chill out!' if you yell at him.
#He says 'Fine. Be that way!' if you address him without actually saying
#anything.
#He answers 'Whatever.' to anything else.

# Skeleton file for the Python "Bob" exercise. Was as follows: 
#
# Skeleton file for the Python "Bob" exercise.
#
#def hey(what):
#
#    return

def hey(_, sentance):
	if sentance.endswith("?"):
		return "Sure."
	if sentance.isupper():
		return "Whoa, chill out!"
    if sentance is None or message.strip == " ":
    	return "Fine, Be that way!"
    else: 
    	return "Whatever."
