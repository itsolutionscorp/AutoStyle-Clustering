import re
from operator import add, sub, truediv, mul


def calculate(question):
    parsed = _parse(question)
    result = next(parsed)
    try:
        while True:
            operator = next(parsed)
            operand = next(parsed)
            result = operator(result, operand)
    except StopIteration:
        return result


WHAT_IS = re.compile(r"(what\s+is)\s*")
NUMBER = re.compile(r"(-?\d+)\s*")
ADD = re.compile(r"plus\s+(-?\d+)\s*")
SUB = re.compile(r"minus\s+(-?\d+)\s*")
MUL = re.compile(r"multiplied\s+by\s+(-?\d+)\s*")
DIV = re.compile(r"divided\s+by\s+(-?\d+)\s*")
POW = re.compile(r"raised\s+to\s+the\s+(-?\d+)(st|nd|rd|th)\s+power\s*")
QUESTION_MARK = re.compile(r"(\?)\s*")

OPERATIONS = {
    ADD: add,
    SUB: sub,
    MUL: mul,
    DIV: truediv,
    POW: pow
}

TOKEN_TYPES = [WHAT_IS, NUMBER, QUESTION_MARK] + OPERATIONS.keys()

def _parse(question):
    tokens = list(_tokenize(question))

    if not tokens or tokens[0].token_type != WHAT_IS:
        raise ValueError("A question must start with 'What is'.")
    tokens.pop(0)

    if not tokens or tokens[-1].token_type != QUESTION_MARK:
        raise ValueError("A question must end with '?'.")
    tokens.pop(-1)

    if not tokens:
        raise ValueError("What is what?")

    token = tokens.pop(0)
    if token.token_type != NUMBER:
        raise ValueError("Expected a number, but got '%s'" % token.match)
    yield int(token.capture) 

    for token in tokens:
        if token.token_type not in OPERATIONS:
            raise ValueError("Expected an operation, but got '%s'" % token.match)
        yield OPERATIONS[token.token_type]
        yield int(token.capture)

class Token:
    def __init__(self, token_type, match, capture):
        self.token_type = token_type 
        self.match = match
        self.capture = capture 

def _tokenize(question):
    question = question.lower()
    while question:
        for TOKEN_TYPE in TOKEN_TYPES:
            m = TOKEN_TYPE.match(question)
            if m:
                yield Token(TOKEN_TYPE, m.group(), m.groups()[0])
                question = question.replace(m.group(), "", 1)
                break
        else:
            raise ValueError("Cannot understand question, starting from '%s'." \
                             % question)
