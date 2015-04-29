#
# Skeleton file for the Python "Bob" exercise.
#


class Preprocessor(object):
    def __init__(self, preprocessor):
        self.preprocessor = preprocessor

    def __call__(self, _):
        def chainable(succ):
            return lambda what: succ(self.preprocessor(what))
        return chainable


class When(object):
    def __init__(self, condition):
        self.condition = condition

    def __call__(self, answer):
        def chainable(succ):
            return lambda what: answer if self.condition(what) else succ(what)
        return chainable


class Answer(object):
    def __init__(self, message):
        self.message = message

    def __call__(self, _):
        return self.message


@Preprocessor(lambda what: what.strip())
def preprocessor_strip():
    pass


@When(lambda what: what.endswith('?') and not what.isupper())
@Answer('Sure.')
def handle_question():
    pass


@When(lambda what: what.isupper())
@Answer('Whoa, chill out!')
def handle_yell(succ):
    pass


@When(lambda what: not what)
@Answer('Fine. Be that way!')
def handle_saying_nothing(succ):
    pass


@When(lambda what: True)
@Answer('Whatever.')
def handle_anything_else(succ):
    pass


@preprocessor_strip
@handle_question
@handle_yell
@handle_saying_nothing
@handle_anything_else
def hey(what):
    pass
