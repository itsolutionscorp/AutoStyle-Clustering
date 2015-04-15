def is_shouting(message):
    return message.isupper()


def is_question(message):
    return message.endswith("?")


def is_silence(message):
    return message == "" or message.isspace()


default_responses = ((is_shouting, "Whoa, chill out!"),
                     (is_question, "Sure."),
                     (is_silence, "Fine. Be that way!"))


def hey(message, responses=default_responses, no_match="Whatever."):
    """ Return a response from a predicate mapping.

    params:
      `responses`: A mapping tuple of (pred-fn, response) pairs.
      `no_match`: A response if no match is found.
    """
    for predicate, response in responses:
        if predicate(message.strip()):
            return response

    return no_match
