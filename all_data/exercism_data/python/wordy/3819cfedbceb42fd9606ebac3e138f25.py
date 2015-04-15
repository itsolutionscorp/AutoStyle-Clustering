import string

def calculate(phrase):
    # strip off extaneous characters
    phrase = string.lstrip(phrase, "What is ")
    phrase = string.rstrip(phrase, "?")
    # separate on whitespace and put into a list
    lst = phrase.split()
    # store the first integer in result
    result = int(lst.pop(0))
    
    # walk the list
    while len(lst) > 0:
        # drop extaneous "by"
        if lst[1] == 'by':
            lst.pop(1)
        # grab the next value/operator pair
        val = int(lst.pop(1))
        oper = lst.pop(0)
        # do the appropriate math
        if oper == "plus":
            result += val
        elif oper == "minus":
            result -= val
        elif oper == "multiplied":
            result *= val
        elif oper == "divided":
            result /= val
        else:
            raise ValueError("not a valid operator")
                
    return result
