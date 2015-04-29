from __future__ import division

ops = ['plus', 'minus', 'multiplied', 'divided']

def calculate(sentence):
    nums = []
    operators = []
    for word in sentence[:len(sentence)-1].split():
        if word in ops:
            operators.append(word)
        try:
            nums.append(float(word))
        except ValueError:
            pass
    if len(nums) != len(operators)+1:
        raise ValueError
    x = operate(nums[0], nums[1], operators[0])
    if len(operators) > 1:
        x2 = 0
        for i in xrange(2, len(nums)):
            x2 = operate(x, nums[i], operators[i-1])
        return x2
    return x
    
def operate(a, b, function):
    if function == 'plus':
        return a+b
    elif function == 'minus':
        return a-b
    elif function == 'multiplied':
        return a*b
    elif function == 'divided':
        return a/b
