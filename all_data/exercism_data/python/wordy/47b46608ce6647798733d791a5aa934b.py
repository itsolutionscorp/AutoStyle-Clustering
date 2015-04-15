import re

OPERATIONS = {
    'plus': lambda x, y: x + y,
    'minus': lambda x, y: x - y,
    'times': lambda x, y: x * y,
    'multiplied': lambda x, y: x * y,
    'divided': lambda x, y: x / y
}

def calculate(question):
    numbers, operators = _parse(question)
    answer = OPERATIONS[operators.pop(0)](numbers.pop(0), numbers.pop(0))

    while operators:
        answer = OPERATIONS[operators.pop(0)](answer, numbers.pop(0))
    return answer

def _parse(question):
    numbers = list(map(int, re.findall(r"-?\d+", question)))
    operators = re.findall(r"plus|minus|times|multiplied|divided", question)
    _validate(operators, numbers)
    return (numbers, operators)

def _validate(operators, numbers):
    if not operators:
        raise ValueError
    if len(numbers) - len(operators) != 1:
        raise ValueError
