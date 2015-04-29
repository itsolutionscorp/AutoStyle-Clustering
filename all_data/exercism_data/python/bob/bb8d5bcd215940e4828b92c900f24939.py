# tests if thing_said ends with a question mark
def _is_question(thing_said):
	return thing_said.endswith('?')

# tests if thing_said is in all caps
def _is_allcaps(thing_said):
	return thing_said.isupper()

# tests if thing_said is empty or whitespace
def _is_silence(thing_said):
	return thing_said=='' or thing_said.isspace()

# _response_tests represents tests which are conducted in sequence and the response given for the first test passed.
_response_tests=[[_is_allcaps,'Whoa, chill out!'], [_is_question,'Sure.'], [_is_silence,'Fine. Be that way!']]
_response_tests.append([lambda x:True,'Whatever.']) # add a default response for anything that fails all listed tests.

# finds bob'e appropriate response to the thing which has been said
# only defined when thing_said is of type str
def hey(thing_said):
	for test,response in _response_tests:
		if test(thing_said):
			return response
	return None
