import re
import operator

def expect_string(question, phrases):
    if type(phrases) == str:
        phrases = [phrases]

    for phrase in phrases:
        s = phrase.split()

        for a, b in zip(question, s):
            if a != b:
                break
        else:
            return phrase, question[len(s):]

    raise ValueError("invalid question")

def expect_number(question):
    try:
        return int(re.sub("[^-+\d]+", "", question[0])), question[1:]
    except:
        raise ValueError("expected operand")

operations = {
    "plus": operator.add,
    "minus": operator.sub,
    "multiplied by": operator.mul,
    "times": operator.mul,
    "divided by": operator.truediv,
    "raised to the": operator.pow,
}

def expect_operation(question):
    operation, question = expect_string(question, operations.keys())
    operation = operations[operation]

    if operation == operator.pow:
        tmp = question.pop(0)
        _, question = expect_string(question, "power")
        question = [tmp] + question

    return operation, question

def calculate(question):
    question = question.replace("?", " ?").lower()
    question = question.split()

    _, question = expect_string(question, "what is")

    result = None

    while question != ['?']:
        operand, question = expect_number(question)

        if result is None:
            result = operand
        else:
            result = operation(result, operand)

        try:
            operation, question = expect_operation(question)
        except ValueError:
            if question == ['?']:
                break
            raise

    return result
