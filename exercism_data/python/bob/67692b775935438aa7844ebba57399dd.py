from collections import OrderedDict


def is_shouting(message):
    return message.isupper()


def is_question(message):
    return message.endswith("?")


def is_silence(message):
    return message == "" or message.isspace()


default_responses = OrderedDict({is_shouting: "Whoa, chill out!",
                                 is_question: "Sure.",
                                 is_silence: "Fine. Be that way!"})


def hey(message, responses=default_responses):
    for predicate, response in responses.items():
        if predicate(message.strip()):
            return response

    return "Whatever."
