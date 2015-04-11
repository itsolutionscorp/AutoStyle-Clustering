def hey(question):
    "Bob's method to answer to a sentence"
    if (
        any(map(lambda x: x.isalpha(), question))
        and question.upper() == question
    ):
        return "Woah, chill out!"
    elif question.endswith('?'):
        return "Sure."
    elif not question or question.isspace():
        return "Fine. Be that way!"
    else:
        return "Whatever."
