#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      StickyThink_Dev
#
# Created:     01/12/2014
# Copyright:   (c) StickyThink_Dev 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------

def sieve(variable):

    bank = range(2, variable +1)
    count = 2
    while count != len(bank) +1:
        for i in bank:
            if i != count:
                if i % count == 0:
                    bank.remove(i)
        count += 1
    return bank
