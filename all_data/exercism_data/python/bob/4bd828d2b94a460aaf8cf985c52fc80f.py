class Bob(object):

    QUESTION_RESPONSE = 'Sure.'
    YELLING_RESPONSE = 'Woah, chill out!'
    NORMAL_RESPONSE = 'Whatever.'
    SILENCE_RESPONSE = 'Fine. Be that way!'

    def __init__(self):
        pass

    def clean_input(self, input):
        return input.strip()

    def is_question(self, input):
        return input.endswith('?')

    def hey(self, input):
        input = input.strip()

        if not input:
            return self.SILENCE_RESPONSE

        # upper takes precedence over questions
        if input.isupper():
            return self.YELLING_RESPONSE

        if self.is_question(input):
            return self.QUESTION_RESPONSE

        return self.NORMAL_RESPONSE
