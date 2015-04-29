#Exercism: python bob

punct_list=['.','!','?']

#Check to see if phrase ends with a proper punctuation
def punct_check(phrase):
    for i in punct_list:
        if phrase.endswith(i):
	    return True
    return False

#Check for non-punct blank spaces
def blank_check(phrase):
    if '   ' in phrase:
	return True
    return False

#Check if shouting
def is_shouting(phrase):
    return phrase.isupper()

#Check if asking a question
def is_question(phrase):
    return phrase.endswith('?')

def hey(phrase):
    punct=punct_check(phrase)
    blank=blank_check(phrase)
    shouting=is_shouting(phrase)
    question=is_question(phrase)
    
    if len(phrase) < 1:
	return 'Fine. Be that way!'
    if shouting:
	return 'Woah, chill out!'
    if question:
	return 'Sure.'
    if punct:
	return 'Whatever.'
    if not punct:
	if not blank:
	    return 'Whatever.'	
	if blank:
	    return 'Fine. Be that way!'
	return 'Fine. Be that way!'
        
