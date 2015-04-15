from operator import add, sub, truediv, mul
from math import sqrt
import re

def calculate(question):
    """Calculate the numerical outcome for the textual question.
    The question must follow the format: "What is <expression>?"

    The expression can be built from the following elements:
    - <integer>: A literal integer number (positive or negative)
    - "plus", "minus", "divided by", "times" "multiplied by" or
      their short forms: "+", "-", "/", "*"
    - "raised to the <1st|2nd|..> power"
    - "the square root of <positive integer>" 

    Examples:
    - "What is 2 plus 4 minus 1 times 8?"
    - "What is 8 divided by the square root of 16?"
    - "What is 2 raised to the 10th power"
    """
    return Expression(question).value

class SyntaxHandler(object):
    """The base class for building expression syntax handlers."""

    @classmethod
    def does_handle(cls, data): 
        """Returns a true value when the handler does handle the
        provided data.  The provided data is the question or a
        part of the question that was provided to the Expression.
        """
        return cls.re.match(data) 

    def parse(self, data):
        """Parses data from the provided question data and updates
        the data by removing the handled part from it.
        """
        self.match = self.re.match(data)
        processed_bit = self.match.group()
        return data.replace(processed_bit, "", 1).strip()

    def apply_to_expression(self, expression):
        """Applies the handler to the expression.
        This method is allowed to update the property expression.value.
        It returns a list of syntax handler types than can come after this one.
        """
        raise NotImplementedError("apply_to_expression")

    @staticmethod
    def describe():
        """Describes what type of data the syntax handler expects.
        Used to format error messages.
        """
        raise NotImplementedError("describe")

class StartOfQuestion(SyntaxHandler):
    re = re.compile("what is")

    @staticmethod
    def describe():
        return "'What is'"

    def apply_to_expression(self, expression):
        expression.value = expression.get_next_numeric_value()
        return [Operator, EndOfQuestion]

class NumericValue(SyntaxHandler):
    re = re.compile(r"-?\d+")

    @staticmethod
    def describe():
        return "<number>"

    @property
    def value(self):
        return int(self.match.group())

class SquareRootValue(NumericValue):
    re = re.compile(r"the square root of (\d+)")

    @property
    def value(self):
        return sqrt(int(self.match.groups()[0]))

class Operator(SyntaxHandler):

    @staticmethod
    def describe():
        return "<operator>"

class BasicOperator(Operator):
    re = re.compile(r"(\+|plus|-|minus|\*|times|multiplied by|/|divided by)")
    operators = {
        "plus"          : add,     "+": add,
        "minus"         : sub,     "-": sub,
        "divided by"    : truediv, "/": truediv,
        "multiplied by" : mul,     "*": mul,
        "times"         : mul
    }

    @property
    def operator(self):
        return self.operators[self.match.group()]

    def apply_to_expression(self, expression):
        operand = expression.get_next_numeric_value()
        expression.value = self.operator(expression.value, operand)
        return [Operator, EndOfQuestion]

class PowerOperator(Operator):
    re = re.compile(r"raised to the (-?\d+)(st|nd|rd|th) power")

    def apply_to_expression(self, expression):
        power = int(self.match.groups()[0]) 
        expression.value = pow(expression.value, power)
        return [Operator, EndOfQuestion]

class SquaredOperator(Operator):
    re = re.compile("squared")

    def apply_to_expression(self, expression):
        expression.value = expression.value ** 2
        return [Operator, EndOfQuestion]

class EndOfQuestion(SyntaxHandler):
    re = re.compile(r"\?")

    @staticmethod
    def describe():
        return "'?'"

    def apply_to_expression(self, expression):
        return None

SYNTAX_HANDLERS = [
    StartOfQuestion,
    NumericValue,
    SquareRootValue,
    BasicOperator,
    PowerOperator,
    SquaredOperator,
    EndOfQuestion 
]

class UnexpectedEndOfQuestion(ValueError): pass
class UnexpectedDataInQuestion(ValueError): pass

class Expression(object):

    def __init__(self, question):
        normalized = self._normalize(question)
        self.handlers = self._parse(normalized)
        self._evaluate()

    def _normalize(self, question):
        normalized = question.lower().strip()
        normalized = " ".join(normalized.split())
        return normalized

    def _parse(self, data):
        while data:
            for SYNTAX_HANDLER in SYNTAX_HANDLERS:
                if SYNTAX_HANDLER.does_handle(data):
                    handler = SYNTAX_HANDLER()
                    data = handler.parse(data)
                    yield handler
                    break
            else:
                raise UnexpectedDataInQuestion(
                    "Cannot understand question, starting from '%s'." % data)

    def _evaluate(self):
        expected = [StartOfQuestion]
        while True:
            handler = self._get_next(expected)
            expected = handler.apply_to_expression(self)
            if not expected:
                break

    def _get_next(self, expected_handler_types):
        def expected():
            return " or ".join(
                t.describe() for t in expected_handler_types)
        try:
            handler = next(self.handlers)
            for expected_handler_type in expected_handler_types:
                if isinstance(handler, expected_handler_type):
                    break
            else:
                raise UnexpectedDataInQuestion(
                    "Expected %s, but got %s." \
                    % (expected(), handler.describe()))
            return handler
        except StopIteration:
            raise UnexpectedEndOfQuestion(
                "Unexpected End of Question, expected %s." % expected())
         
    def get_next_numeric_value(self):
        return self._get_next([NumericValue]).value
