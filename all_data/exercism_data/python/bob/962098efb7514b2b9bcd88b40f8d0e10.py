#
# Skeleton file for the Python "Bob" exercise.
#


def processer_strip(succ):
    def processer(what):
        return succ(what.strip())
    return processer


def handle_question(succ):
    def handler(what):
        if what.endswith('?') and not what.isupper():
            return 'Sure.'
        else:
            return succ(what)
    return handler


def handle_yell(succ):
    def handler(what):
        if what.isupper():
            return 'Whoa, chill out!'
        else:
            return succ(what)
    return handler


def handle_saying_nothing(succ):
    def handler(what):
        if not what:
            return 'Fine. Be that way!'
        else:
            return succ(what)
    return handler


def handle_anything_else(succ):
    def handler(what):
        return 'Whatever.'
    return handler


@processer_strip
@handle_question
@handle_yell
@handle_saying_nothing
@handle_anything_else
def hey(what):
    pass
