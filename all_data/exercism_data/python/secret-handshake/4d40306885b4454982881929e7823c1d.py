'''exer secret-handshake'''

#     1 = wink (1)
#    10 = double blink (2)
#   100 = close your eyes (4)
#  1000 = jump (8)
# 10000 = Reverse the order of the operations in the secret handshake. (16)
# The program should consider strings specifying an invalid binary as the
# value 0.

# listed from most significant to least
CODE_TABLE = [(16, 'reverse ops'), (8, 'jump'), (4, 'close your eyes'),
            (2, 'double blink'), (1, 'wink')]

def handshake(int_or_bstr):
    '''convert integer or binary string to CODE_TABLE and return'''
    if type(int_or_bstr) is str:
        try:
            handshake_val = int(int_or_bstr, 2)
        except ValueError:
            handshake_val = 0
    else:
        handshake_val = int(int_or_bstr)

    handshake_code = []
    frev = True             # we are working it backwards, so correct it
    while handshake_val > 0:
        for cval, ccode in CODE_TABLE:
            if cval <= handshake_val:
                handshake_val -= cval
                if cval == 16:
                    frev = not frev  # reverse ops
                else:
                    handshake_code.append(ccode)
    if frev:
        handshake_code.reverse()

    return handshake_code

def code(code_list):
    '''convert list of codes back to a binary string'''
    msg_val = 0
    cval_order = []
    for code_el in code_list:
        matched = False
        for cval, ccode in CODE_TABLE:
            if code_el == ccode:
                msg_val += cval
                cval_order.append(cval)
                matched = True

        if not matched:     # guard for unknown codes
            msg_val = 0
            break

    if cval_order != sorted(cval_order):  # detect reverse ops
        if cval_order != list(reversed(sorted(cval_order))):
            # it is neither in ascending or descending order, so invalid or
            # is it intended to represent 0
            msg_val = 0
        else:
            msg_val += 16

    return "{0:b}".format(msg_val)


def main():
    """test mmackay: Does ['wink','jump','double blink'] result in the same
                     code as ['double blink','jump','wink']"""
    # partially reversed is undefined, should we throw an exception?
    # OR return a 0 like we do with an invalid binary string?
    assert code(['wink', 'jump', 'double blink']) == code(['double blink',
                                                           'jump', 'wink'])
    assert code(['wink', 'jump', 'double blink']) == '0'
    assert code(['wink', 'double blink']) != code(['double blink', 'wink'])

if __name__ == '__main__':
    main()
