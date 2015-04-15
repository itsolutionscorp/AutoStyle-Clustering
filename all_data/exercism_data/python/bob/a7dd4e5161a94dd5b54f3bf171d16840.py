def hey(comment):
    if isEmpty(comment):
        return 'Fine. Be that way!'
    if isShouting(comment):
        return 'Whoa, chill out!'
    if comment.endswith('?'):
        return 'Sure.'

    return 'Whatever.'


def isEmpty(line):
    return line.isspace() or len(line) == 0

def isShouting(line):
    result = False

    for c in line:
          if c.isalpha():
             if not c.isupper():
                return False
             else: result = True

    return result
