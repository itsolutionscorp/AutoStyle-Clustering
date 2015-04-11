def sum_of_squares(sqs):
    counter = 0
    for x in range (1, sqs + 1):
        counter += x**2
    return counter

def square_of_sum(sm):
    counter = 0
    for x in range (1, sm + 1):
        counter += x
    return counter**2

def difference(sqs_sm):
    sqs_sm = sum_of_squares(sqs_sm) - square_of_sum(sqs_sm)
    return abs(sqs_sm)
