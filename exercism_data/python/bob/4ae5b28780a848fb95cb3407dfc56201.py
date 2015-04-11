# -*- coding: utf-8 -*-
"""
Implements Bob, a teenager with a limited set of responses to statements and
questions.

Bob answers 'Sure.' if you ask him a question.
He answers 'Whoa, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying
anything.
He answers 'Whatever.' to anything else.
"""

class Response:
    """
    Simple object to store responses and likelihoods of correctness
    """
    QUESTION_RESPONSE = "Sure."
    YELL_RESPONSE = "Whoa, chill out!"
    NOTHING_SAID_RESPONSE = "Fine. Be that way!"
    DEFAULT_RESPONSE = "Whatever."

    def __init__(self, score=0, answer="Whatever"):
        self.score = score
        self.answer = answer

    def set_response(self, score, answer):
        self.score = score
        self.answer = answer


def hey(what):
    """
    Ask a question to bob by passing a string to this method
    :param what: The question or statement you want to make to Bob
    :return: Bob's response
    """
    responses = [analyze_question_hints(what), analyze_yell_hints(what),
                 analyze_empty_ask(what)]
    answer = Response.DEFAULT_RESPONSE
    high_score = 0
    for response in responses:
        if response.score > high_score:
            answer = response.answer
    return answer


def analyze_question_hints(what):
    """
    Analyze input to Bob for hints that it is a question
    :param what: The question or statement you want to make to Bob
    :return: A Response object. Contains the strongest question signal picked up
    or default response otherwise
    """
    result = Response()
    if what.endswith('?'):
        result.set_response(90, Response.QUESTION_RESPONSE)
    if what.startswith("What are"):
        result.set_response(80, Response.QUESTION_RESPONSE)
    if contains_word(what, "what"):
        result.set_response(30, Response.QUESTION_RESPONSE)
    return result


def analyze_yell_hints(what):
    """
    Analyze input to Bob for hints that it is someone yelling
    :param what: The question or statement you want to make to Bob
    :return: A Response object. Contains the strongest yelling signal picked up
    or default response otherwise
    """
    result = Response()
    if what.isupper():
        result.set_response(95, Response.YELL_RESPONSE)
    return result


def analyze_empty_ask(what):
    """
    Analyze input to Bob for hints that it is empty
    :param what: The question or statement you want to make to Bob
    :return: A Response object. Contains the strongest signal that the input is
    empty or default response otherwise
    """
    result = Response()
    if is_whitespace_only(what):
        result.set_response(100, Response.NOTHING_SAID_RESPONSE)
    return result


def contains_word(input, search_word, case_insensitive=True):
    """
    Search for the existence of a word in a string. Substrings count as a
    match
    :param input: String to be searched
    :param search_word: Word that you want to find
    :param case_insensitive: Whether or not the search should be carried out
    insensitive to case. Defaults to case INsensitive
    :return: Returns true if match found and false otherwise
    """
    if case_insensitive:
        input = input.lower()
        search_word = search_word.lower()
    split_sent = input.split(" ")
    result = False
    for word in split_sent:
        if word.find(search_word) != -1:
            result = True
    return result


def is_whitespace_only(input):
    """
    Determines if an input contains only white space
    :param input: Input to be checked for whitespace only
    :return: True if there are only whitespace characters. False otherwise
    """
    input = input.replace(" ", '').replace("\t", '')
    if len(input) == 0:
        return True
    else:
        return False
