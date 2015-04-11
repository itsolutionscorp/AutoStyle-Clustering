def get_raw_arguments(word_problem):
    if not 'What is' in word_problem:
        raise ValueError
    word_problem = word_problem.split('What is ')[1]
    word_problem = word_problem.replace(' by','').replace('?','')
    word_problem = word_problem.split(' ')
    return word_problem


def calculate_tuple(raw_args):
    if raw_args[1] == 'plus':
        return int(raw_args[0]) + int(raw_args[2])
    elif raw_args[1] == 'minus':
        return int(raw_args[0]) - int(raw_args[2])
    elif raw_args[1] == 'divided':
        return int(raw_args[0]) / int(raw_args[2])
    elif raw_args[1] == 'multiplied':
        return int(raw_args[0]) * int(raw_args[2])
    else:
        raise ValueError


def calculate(word_problem):
    raw_args = get_raw_arguments(word_problem)
    if len(raw_args) == 3:
        return calculate_tuple(raw_args)
    elif len(raw_args) == 5:
        raw_2nd = raw_args[3:]
        result = calculate_tuple(raw_args)
        raw_2nd.insert(0, result)
        result_two = calculate_tuple(raw_2nd)
        return result_two
    else:
        raise ValueError
