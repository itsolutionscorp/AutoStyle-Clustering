def calculate(question):
    q_list = question.split()[2:]
    operations = []
    for i in xrange(len(q_list)):
        if i == len(q_list)-1:
            operations.append(int(q_list[i][:-1]))
        else:
            try:
                operations.append(int(q_list[i]))
            except ValueError:
                if not operations:
                    raise ValueError("Int should follow initial 'What is...'")
                if type(operations[-1]) is int:
                    operations.append(q_list[i])
                else:
                    operations[-1] = operations[-1] + " " + q_list[i]
    out = operations[0]
    for i in xrange((len(operations)-1)/2):
        op = operations[2*i + 1]
        opint = operations[2*(i+1)]
        if not (type(op) is str and type(opint) is int):
            raise ValueError("Incorrect sequence of inputs.") 
        if op == 'plus':
            out += opint
        elif op == 'minus':
            out -= opint
        elif op == 'multiplied by':
            out *= opint
        elif op == 'divided by':
            out /= opint
        else:
            raise ValueError("Operation not recognized.")
    return out
