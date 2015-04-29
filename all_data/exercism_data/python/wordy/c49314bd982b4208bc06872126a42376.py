"""Wordy"""

import itertools
import re


def calculate(query):
    """Answer a word problem as an integer."""
    try:
        lexer = Lexer(query)
        answer = Parser(lexer.lex()).parse_query()
        return answer
    except Error:
        raise ValueError("Ill-formed question")


class Error(Exception):
    """Wordy Error"""
    pass


class LexError(Error):
    """Lex Error"""
    pass


class ParseError(Error):
    """Parse Error"""
    pass


class Token(object):
    """Token"""

    # token types
    WHAT = "WHAT"
    IS = "IS"
    PLUS = "PLUS"
    MINUS = "MINUS"
    TIMES = "TIMES"
    MULTIPLIED = "MULTIPLIED"
    DIVIDED = "DIVIDED"
    BY = "BY"
    QUESTION_MARK = "QUESTION_MARK"
    UNARY_MINUS = "UNARY_MINUS"
    INTEGER = "INTEGER"
    EOF = "EOF"

    def __init__(self, type_, value, pos):
        self.type = type_
        self.value = value
        self.pos = pos

    def __repr__(self):
        cls = self.__class__
        return "{!s}.{!s}({!r}, {!r}, {!r})".format(
            cls.__module__, cls.__name__, self.type, self.value, self.pos)


class Lexer(object):
    """Lexer"""

    WHITESPACE = re.compile(r"\s+")

    TOKEN_TYPE_TO_REGEXP = (
        (Token.WHAT, re.compile(r"\bWhat\b")),
        (Token.IS, re.compile(r"\bis\b")),
        (Token.PLUS, re.compile(r"\bplus\b")),
        (Token.MINUS, re.compile(r"\bminus\b")),
        (Token.TIMES, re.compile(r"\btimes\b")),
        (Token.MULTIPLIED, re.compile(r"\bmultiplied\b")),
        (Token.DIVIDED, re.compile(r"\bdivided\b")),
        (Token.BY, re.compile(r"\bby\b")),
        (Token.QUESTION_MARK, re.compile(r"\?")),
        (Token.UNARY_MINUS, re.compile("-")),
        (Token.INTEGER, re.compile(r"\d+"))
    )

    def __init__(self, line):
        self.line = line
        self.pos = 0

    def lex(self):
        """Generate tokens."""
        while self.pos < len(self.line):
            # Ignore whitespace.
            matched = Lexer.WHITESPACE.match(self.line, self.pos)
            if matched is not None:
                self.pos += len(matched.group())
                continue

            for token_type, regexp in Lexer.TOKEN_TYPE_TO_REGEXP:
                matched = regexp.match(self.line, self.pos)
                if matched is not None:
                    yield Token(token_type, matched.group(), self.pos)
                    self.pos += len(matched.group())
                    break
            else:
                raise LexError("{}: {}".format(self.pos, self.line[self.pos:]))

        # signal EOF.
        yield Token(Token.EOF, None, None)


class Parser(object):
    """Parser

    QUERY ::= WHAT IS EXPRESSION QUESTION_MARK EOF
    EXPRESSION ::= TERM ((PLUS | MINUS | MULTIPLIED BY | DIVIDED BY) TERM)*
    TERM ::= FACTOR (TIMES FACTOR)*
    FACTOR ::= UNARY_MINUS? INTEGER
    """

    def __init__(self, tokens):
        self.tokens = iter(tokens)

    def parse_query(self):
        """QUERY ::= WHAT IS EXPRESSION QUESTION_MARK EOF"""
        self.expect(Token.WHAT)
        self.expect(Token.IS)
        expr = self.parse_expression()
        self.expect(Token.QUESTION_MARK)
        self.expect(Token.EOF)
        return expr

    def parse_expression(self):
        """EXPRESSION ::= TERM ((PLUS | MINUS | MULTIPLIED BY | DIVIDED BY) TERM)*"""
        expr = self.parse_term()
        while True:
            peek, self.tokens = itertools.tee(self.tokens)
            try:
                token = next(peek)
            except StopIteration:
                break
            if token.type == Token.PLUS:
                self.tokens = peek
                term = self.parse_term()
                expr += term
            elif token.type == Token.MINUS:
                self.tokens = peek
                term = self.parse_term()
                expr -= term
            elif token.type == Token.MULTIPLIED:
                self.tokens = peek
                self.expect(Token.BY)
                term = self.parse_term()
                expr *= term
            elif token.type == Token.DIVIDED:
                self.tokens = peek
                self.expect(Token.BY)
                term = self.parse_term()
                expr /= term
            else:
                break
        return expr

    def parse_term(self):
        """TERM ::= FACTOR (TIMES FACTOR)*"""
        term = self.parse_factor()
        while True:
            peek, self.tokens = itertools.tee(self.tokens)
            try:
                token = next(peek)
            except StopIteration:
                break
            if token.type == Token.TIMES:
                self.tokens = peek
                factor = self.parse_factor()
                term *= factor
            else:
                break
        return term

    def parse_factor(self):
        """FACTOR ::= UNARY_MINUS? INTEGER"""
        try:
            token = next(self.tokens)
        except:
            raise ParseError("UNARY_MINUS or INTEGER expected, but not found")
        if token.type == Token.UNARY_MINUS:
            token = self.expect(Token.INTEGER)
            return -(int(token.value))
        elif token.type == Token.INTEGER:
            return int(token.value)
        else:
            raise ParseError(
                "UNARY_MINUS or INTEGER expected, but got {}".format(token))

    def expect(self, token_type):
        """Read the next token if it is of a given type.

        :raise ParseError: if there is no more token or the next token is not
                           of the given type.
        """
        try:
            token = next(self.tokens)
        except StopIteration:
            raise ParseError("{} expected, but not found".format(token_type))
        if token.type != token_type:
            raise ParseError(
                "{} expected, but got {}".format(token_type, token))
        return token
