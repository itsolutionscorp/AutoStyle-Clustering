__author__ = 'Arno'

def is_content(line):
    content_or_not = False
    for i in line:
        if i.isalnum():
            content_or_not = True
    return content_or_not

def hey(prompt):
    if prompt.isupper():
        return 'Whoa, chill out!'
    elif len(prompt) > 0 and prompt[len(prompt)-1] == '?':
        return 'Sure.'
    elif not is_content(prompt):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

print hey('...')
print hey('@#$%^')
print hey('PRERELOPA')
