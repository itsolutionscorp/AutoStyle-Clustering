# coding: utf-8


def hey(what):
    """Simulate Bob's response.

    Bob answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without
    actually saying anything.
    He answers 'Whatever.' to anything else.
    """

    try:
        what_stripped = what.strip()
    except AttributeError:
        print("Error: Please input string object!")
        return ""
    else:
        if what_stripped == "":
            return "Fine. Be that way!"
        elif what_stripped.isupper():
            return "Whoa, chill out!"
        elif what_stripped.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
