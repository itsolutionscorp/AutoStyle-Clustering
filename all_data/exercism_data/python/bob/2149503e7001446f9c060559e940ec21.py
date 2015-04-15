def hey(input):
    if len(input) == 0 or '\t' in input:
        return "Fine. Be that way!"
    elif input[-1] == "?" and input != input.upper():
        return "Sure."
    elif is_number(input[0]) and input[-1] == "?":
        return "Sure."
    elif is_number(input[-1]):
        return "Whatever."
    elif input == input.upper():
        return "Woah, chill out!"
    else:
        return "Whatever."

def is_number(string):
    try:
        int(string)
        return True
    except ValueError:
        return False
