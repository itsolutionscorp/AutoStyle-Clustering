def _yelling(input):
    return input.upper() == input


def _any(input):
    return True


def _question(input):
    return input.endswith("?")


def _silence(input):
    return not input


RESPONSES = (
    (_silence, "Fine, be that way."),
    (_yelling, "Woah, chill out!"),
    (_question, "Sure."),
    (_any, "Whatever."),
)


class Bob(object):
    def hey(self, input):
        for check_fn, response in RESPONSES:
            if check_fn(input):
                return response
