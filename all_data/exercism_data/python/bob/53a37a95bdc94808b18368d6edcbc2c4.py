class bob(object):
    """
    Bob is a lackadaisical teenager. In conversation, his responses are very limited.

    Bob answers 'Sure.' if you ask him a question.

    He answers 'Whoa, chill out!' if you yell at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else.
    """
    @staticmethod
    def hey(statement):
        # Saying nothing
        if not statement or statement.isspace():
            return 'Fine. Be that way!'

        # Yelling
        if statement.isupper():
            return 'Whoa, chill out!'

        # Question
        if statement[-1] == '?':
            return 'Sure.'

        # Anything Else
        return 'Whatever.'
