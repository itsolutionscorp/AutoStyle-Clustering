class Bob(object):
    """
    Meet Bob:

        Bob is a lackadaisical teenager. In conversation, his responses are very limited.

        Bob answers 'Sure.' if you ask him a question.

        He answers 'Woah, chill out!' if you yell at him (ALL CAPS).

        He says 'Fine. Be that way!' if you address him without actually saying anything.

        He answers 'Whatever.' to anything else."

    Use the hey() function to say something to Bob.
    """

    def hey(self, input_text):
        """Selects response function depending on the input text."""

        if hasattr(input_text, 'strip'):
            input_text = input_text.strip()

        if not input_text:
            response_func = 'silence'
        elif input_text == input_text.upper():
            response_func = 'shouting'
        elif input_text[-1] == '?':
            response_func = 'question'
        else:
            response_func = 'default'

        return getattr(self, 'respond_to_' + response_func)()

    def respond_to_shouting(self):
        return 'Woah, chill out!'

    def respond_to_default(self):
        return 'Whatever.'

    def respond_to_question(self):
        return 'Sure.'

    def respond_to_silence(self):
        return 'Fine. Be that way!'
