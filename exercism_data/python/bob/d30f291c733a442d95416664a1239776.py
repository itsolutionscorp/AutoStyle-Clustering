 # -*- coding: utf-8 -*-

import re;

def hey(stm):
	# Variables with responses.
	whatEverResponse = 'Whatever.';
	questionResponse = 'Sure.';
	yellResponse = 'Whoa, chill out!';
	nothingResponse = 'Fine. Be that way!';

	# If it's not an instance of a string, it'll just return 'Whatever.'.
	if isinstance(stm, str) or isinstance(stm, unicode):
		if len(stm.strip()) == 0:
			return nothingResponse;
		elif re.search('[a-zA-Z]', stm) is not None and stm.upper() == stm:
			return yellResponse;
		elif stm.endswith('?'):
			return questionResponse;
		else:
			return whatEverResponse;
	else:
		return whatEverResponse;
