#!/usr/bin/env python

#Response vars
q_resp = 'Sure.'
y_resp = 'Whoa, chill out!'
e_resp = 'Fine. Be that way!'
d_resp = 'Whatever.'

#hey method called in test file
def hey(statement):
    if statement.endswith('.'):
        return d_resp
    elif statement.endswith('?') and not statement.isupper():
        return q_resp
    elif statement.endswith('!') and not statement.isupper():
        return d_resp
    elif statement.isupper() or statement.endswith('!'):
        return y_resp
    elif statement.strip() == '':
        return e_resp
    else:
        return d_resp

def main():
    pass

if __name__ == '__main__':
    main()
