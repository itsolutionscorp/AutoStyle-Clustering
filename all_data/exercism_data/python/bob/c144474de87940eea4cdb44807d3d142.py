import re


class Bob():

    def hey(self, words):

        # Am I being ignored?
        # (Only whitespace or empty)
        if re.search(r'^[\s]*$', words):
            return 'Fine. Be that way!'

        # Am I being yelled at?
        # (No lowercase present)
        elif re.search(r'^[^a-z\xe0-\xff]+$', words) and re.search(r'[A-Z\xc0-\xdf]', words):
            return 'Woah, chill out!'

        # Am I being questioned?
        # (Ends with a question mark)
        elif re.search(r'.*\?$', words):
            return 'Sure.'

        # Am I being engaged in boring conversation?
        # (Anything else)
        # elif re.match(r'\S', words):
        #    return 'Whatever.'
        else:
            return 'Whatever.'
