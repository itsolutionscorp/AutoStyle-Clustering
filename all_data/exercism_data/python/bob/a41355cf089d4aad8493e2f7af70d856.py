"""Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
He says 'Fine. Be that way!' if you address him without actually saying anything.
He answers 'Whatever.' to anything else."""


class Bob(object):
    class Response(object):
        QUESTION = 'Sure.'
        SHOUT    = 'Woah, chill out!'
        SILENCE  = 'Fine. Be that way!'
        DEFAULT  = 'Whatever.'

    def hey(self, message):
        if not message.strip():
            message_type = 'SILENCE'
        elif message.isupper():
            message_type = 'SHOUT'
        elif message.endswith('?'):
            message_type = 'QUESTION'
        else:
            message_type = 'DEFAULT'

        return getattr(self.Response, message_type)
