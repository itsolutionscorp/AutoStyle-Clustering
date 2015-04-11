import re

class Bob(object):
    question_re = r'\?$'
    shout_re = r'^[^a-z]*[A-Z][^a-z]*$'
    silence_re = r'^\s*$'

    responses = [
            (shout_re, 'Woah, chill out!'),
            (question_re, 'Sure.'),
            (silence_re, 'Fine. Be that way!'),
            ('.*', 'Whatever.'),
            ]

    def hey(self, message):
        if message is None:
            return 'Fine. Be that way!'
        else:
            for pattern, response in self.responses:
                if re.search(pattern, message):
                    return response
