def hey(question):
    question = question.strip()
    if not question:
        return "Fine. Be that way!"
    if question.isupper():
        return "Whoa, chill out!"
    if question.endswith('?'):
        return "Sure."
    return "Whatever."
