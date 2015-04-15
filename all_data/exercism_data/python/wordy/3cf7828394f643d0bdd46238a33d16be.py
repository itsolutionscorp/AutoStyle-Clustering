def calculate(question):

  question = question[8:]

  elements = question.split(' ')

  first = int(elements[0])

  return single_operation(elements[1:], first)


def single_operation(elements, first):

  operation = elements[0]

  if operation not in ["plus", "minus", "multiplied", "divided"]:
    raise ValueError

  final = False

  if operation in ["plus", "minus"]:
    second = elements[1]
    if '?' in second:
      second = int(second[:-1])
      final = True
    else:
      second = int(second)
      next_op = elements[2:]
  else:
    second = elements[2]
    if '?' in second:
      second = int(second[:-1])
      final = True
    else:
      second = int(second)
      next_op =  elements[3:]

  result = 0
  if operation == "plus":
    result = first + second
  elif operation == "minus":
    result = first - second
  elif operation == "multiplied":
    result = first * second
  else:
    result = first / second

  if final:
    return result
  else:
    return single_operation(next_op, result)
