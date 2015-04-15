import math

def encode(input):
    def alnums(input):
        return ''.join([x for x in input if x.isalnum()])
    input = alnums(input.lower())
    WIDTH = math.ceil(math.sqrt(len(input)))
    return ' '.join([''.join([input[x] for x in range(col, len(input), WIDTH)]) for col in range(0, WIDTH)])
