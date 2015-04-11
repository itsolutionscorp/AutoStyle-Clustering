import re

class Bob:
	
    """Bob class
	Bob is a lackadaisical teenager. In conversation, his responses are very limited.

	Bob answers 'Sure.' if you ask him a question.

	He answers 'Whoa, chill out!' if you yell at him.

	He says 'Fine. Be that way!' if you address him without actually saying
	anything.

	He answers 'Whatever.' to anything else."""

    def hey(self,string):
		
        shout_pat = '[A-Z]{2,}'
        question_pat = '\?$'
        white_space_pat = '\s+'
        contains_alpha_pat = '[a-zA-Z]+'
        any_text_pat = '[\S+]'
		
        contains_shouting = re.search(shout_pat, string)
        asking_question = re.search(question_pat, string)
        white_space = re.search(white_space_pat, string)
        contain_alpha = re.search(contains_alpha_pat, string)
        any_text = re.search(any_text_pat, string)

        string_passed_in = string
        string_passed_in_upper = string_passed_in.upper()
        shouting = string_passed_in == string_passed_in_upper
		
        if asking_question and (not contains_shouting or contains_shouting.group(0) == 'OK'):
            return 'Sure.'
        if shouting and contain_alpha:
            return 'Whoa, chill out!'
        if (white_space and not any_text) or string == '':
            return 'Fine. Be that way!'
			
        return 'Whatever.'
	
