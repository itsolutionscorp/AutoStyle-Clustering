'''exer secret-handshake'''

#     1 = wink (1)
#    10 = double blink (2)
#   100 = close your eyes (4)
#  1000 = jump (8)
# 10000 = Reverse the order of the operations in the secret handshake. (16)
# The program should consider strings specifying an invalid binary as the
# value 0.

THE_CODE = [(1, 'wink'), (2, 'double blink'), (4, 'close your eyes'),
            (8, 'jump'), (16, 'reverse ops')]
THE_CODE.reverse()  # sort big to small

def handshake(i_or_bs):
    '''convert integer or binary string to THE_CODE and return'''
    if type(i_or_bs) is str:
        # cvt to int
        try:
            hval = int(i_or_bs, 2)
        except ValueError:
            hval = 0
    else:
        hval = i_or_bs

    hcode = []
    frev = True             # we are working it backwards, so correct it
    while hval > 0:
        for cval, ccode in THE_CODE:
            if cval <= hval:
                hval -= cval
                if cval == 16:
                    frev = not frev  # reverse ops
                else:
                    hcode.append(ccode)
    if frev:
        hcode.reverse()

    return hcode

def code(clist):
    '''convert list of codes back to a binary string'''
    mval = 0             # message value
    cval_order = []
    for code_el in clist:
        matched = False
        for cval, ccode in THE_CODE:
            if code_el == ccode:
                mval += cval
                cval_order.append(cval)
                matched = True

        if not matched:     # guard for unknown codes
            mval = 0
            break

    if cval_order != sorted(cval_order):  # detect reverse ops
        mval += 16

    return "{0:b}".format(mval)   # cvt to binary str and return
