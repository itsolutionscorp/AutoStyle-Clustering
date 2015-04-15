#!/usr/bin/env python

#Response vars
q_resp = 'Sure.'
y_resp = 'Whoa, chill out!'
e_resp = 'Fine. Be that way!'
d_resp = 'Whatever.'

def hey(statement):
    if len(statement.strip()) == 0:
        return e_resp
    elif statement.isupper():
        return y_resp
    elif statement.endswith('?'):
        return q_resp
    else:
        return d_resp

def main():
    pass

if __name__ == '__main__':
    main()
