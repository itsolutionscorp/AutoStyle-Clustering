from operator import add, sub, truediv, mul
from math import sqrt
from itertools import repeat, chain
import re


__all__ = ['calculate']

def calculate(question):
    """Return the numerical outcome for the textual question.
    The question must follow the format: "What is <expression>?"

    The <expression> can be built from the following elements:
    - "plus", "minus", "divided by", "times" "multiplied by" or
      their short forms: "+", "-", "/", "*"
    - "raised to the <1st|2nd|..> power"
    - <integer>: A literal integer number (positive or negative)
    - "the square root of <positive integer>" 
    - "squared", which squares the result of everything before it
    - "rounded", which rounds the result of everything before it
    - "rounded with precision <number>", which does the same but
      using the provided precision in decimal digits

    Examples:
    - "What is 2 plus 4 minus 1 times 8?" -> 40
    - "What is 8 divided by the square root of 16?" -> 2.0
    - "What is 2 raised to the 10th power" -> 1024
    - "What is 2 plus 6 squared?" -> 64
    - "What is the square root of 5 rounded?" -> 2.0
    - "What is the square root of 5 rounded with precision 2?" -> 2.24
    """
    return Expression(question).value


# ----------------------------------------------------------------------
# Base classes for syntax handling
# ----------------------------------------------------------------------

class SyntaxHandler(object):
    """The base class for building expression syntax handlers."""

    @classmethod
    def regexp(cls):
        """Return a compiled regular expression, describing the pattern
        that is handled by the syntax handler.
        """
        try:
            return cls.__regexp
        except AttributeError:
            cls.__regexp = re.compile(cls.pattern)
            return cls.__regexp

    @classmethod
    def does_handle(cls, data): 
        """Return a true value when the handler does handle the data.
        The provided data is the not yet processed part of the question.
        """
        return cls.regexp().match(data) 

    def parse(self, data):
        """Parse data from the provided question data, update
        the data by removing the handled part from it and return
        the updated data.
        """
        self.match = self.regexp().match(data)
        processed_bit = self.match.group()
        return data.replace(processed_bit, "", 1).strip()

    @staticmethod
    def describe():
        """Return a textual description of the type of data the syntax
        handler expects.  Used to format error messages.
        """
        raise NotImplementedError("describe")

    @classmethod
    def describe(cls):
        return cls.description

class Operation(SyntaxHandler):
    """The base class for implementing operations."""
    description = "<operation>"

    def apply_to_expression(self, expression):
        """Apply the operator to the expression.
        This method is allowed to update expression.value.
        """
        raise NotImplementedError("apply_to_expression")

class NumericValue(SyntaxHandler):
    """The base class for implementing numeric values."""
    description = "<numeric value>"

    @property
    def value(self):
        raise NotImplementedError("value")


# ----------------------------------------------------------------------
# Syntax handling
# ----------------------------------------------------------------------

class StartOfQuestion(Operation):
    pattern = "what is"
    description = "'What is'"

    def apply_to_expression(self, expression):
        expression.value = expression.get_next_numeric_value()

class NumericLiteralValue(NumericValue):
    pattern = r"-?\d+"

    @property
    def value(self):
        return int(self.match.group())

class SquareRootValue(NumericValue):
    pattern = r"the square root of (\d+)"

    @property
    def value(self):
        return sqrt(int(self.match.groups()[0]))

class BasicOperator(Operation):
    pattern = r"(\+|plus|-|minus|\*|times|multiplied by|/|divided by)"
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

class PowerOperator(Operation):
    pattern = r"raised to the (-?\d+)(st|nd|rd|th) power"

    def apply_to_expression(self, expression):
        power = int(self.match.groups()[0]) 
        expression.value = pow(expression.value, power)

class SquaredOperator(Operation):
    pattern = "squared"

    def apply_to_expression(self, expression):
        expression.value = expression.value ** 2

class RoundedOperator(Operation):
    pattern = "rounded(?: with precision (-?\d+))?"

    def apply_to_expression(self, expression):
        precision = int(self.match.groups()[0] or 0)
        expression.value = round(expression.value, precision)

class EndOfQuestion(Operation):
    pattern = r"\?"
    description = "'?'"

    def apply_to_expression(self, expression):
        return


# ----------------------------------------------------------------------
# Question evaluation
# ----------------------------------------------------------------------

class UnexpectedEndOfQuestion(ValueError): pass
class UnexpectedDataInQuestion(ValueError): pass

class Expression(object):
    """Takes a question as input, to parse and evaluate it.
    After evaluation, the outcome of the question can be found
    in the property 'value'.
    """

    def __init__(self, question):
        normalized = self.__normalize(question)
        self.handlers = self.__parse(normalized)
        self.__evaluate()

    def __normalize(self, question):
        normalized = question.lower().strip()
        normalized = " ".join(normalized.split())
        return normalized

    def __parse(self, data):
        while data:
            for syntax_handler in self.__get_syntax_handlers():
                if syntax_handler.does_handle(data):
                    handler = syntax_handler()
                    data = handler.parse(data)
                    yield handler
                    break
            else:
                raise UnexpectedDataInQuestion(
                    "Cannot understand question, starting from '%s'." % data)

    def __get_syntax_handlers(self):
        handlers  = [ ]
        for base_handler_type in [NumericValue, Operation]:
            handlers.extend(base_handler_type.__subclasses__())
        return handlers

    def __evaluate(self):
        for expected in chain([StartOfQuestion], repeat(Operation)):
            handler = self.__get_next(expected)
            handler.apply_to_expression(self)
            if type(handler) == EndOfQuestion: break

    def __get_next(self, expected_handler_type):
        try:
            handler = next(self.handlers)
            if not isinstance(handler, expected_handler_type):
                raise UnexpectedDataInQuestion(
                    "Expected %s, but got %s." \
                    % (expected_handler_type.describe(), handler.describe()))
            return handler
        except StopIteration:
            raise UnexpectedEndOfQuestion(
                "Unexpected End of Question, expected %s." \
                % expected_handler_type.describe())
         
    def get_next_numeric_value(self):
        """Can be used during evaluation to return the next numeric
        value from the question.  This method is intended to be used by
        the syntax operation handlers.
        """
        return self.__get_next(NumericValue).value
