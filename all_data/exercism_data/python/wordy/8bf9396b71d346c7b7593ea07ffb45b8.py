import re
import operator

OPERATIONS = {
    'plus': operator.add,
    'minus': operator.sub,
    'times': operator.mul,
    'multiplied': operator.mul,
    'divided': operator.div
}

def calculate(question):
    numbers, operators = __parse(question)
    answer = OPERATIONS[operators.pop(0)](numbers.pop(0), numbers.pop(0))

    while operators:
        answer = OPERATIONS[operators.pop(0)](answer, numbers.pop(0))
    return answer

def __parse(question):
    numbers = __parse_numbers(question)
    operators = __parse_operators(question)
    __validate(operators, numbers)
    return (numbers, operators)

def __parse_numbers(question):
    return map(int, re.findall(r"-?\d+", question))

def __parse_operators(question):
    return re.findall(r"plus|minus|times|multiplied|divided", question)

def __validate(operators, numbers):
    if not operators:
        raise ValueError("The problem must contain plus, minus, times, multiplied, or divided.")
    if not __has_one_operator_for_each_operand(operators, numbers):
        raise ValueError("The problem has the wrong number of operands and/or operators.")

def __has_one_operator_for_each_operand(operators, numbers):
    return len(numbers) - len(operators) == 1
