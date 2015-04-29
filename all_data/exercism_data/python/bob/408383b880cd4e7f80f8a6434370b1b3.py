"""
Bob: a lackadaisical teenager.

Bob is a lackadaisical teenager. In conversation, his responses are very
limited. Bob answers 'Sure.' if you ask him a question. He answers 'Woah, chill
out!' if you yell at him. He says 'Fine. Be that way!' if you address him
without actually saying anything. He answers 'Whatever.' to anything else.
"""
class Bob(object):

    @staticmethod
    def hey(statement):

        if statement.isupper():
            return 'Woah, chill out!'
        elif statement.endswith('?'):
            return 'Sure.'
        elif not statement or statement.isspace():
            return 'Fine. Be that way!'

        return 'Whatever.'
