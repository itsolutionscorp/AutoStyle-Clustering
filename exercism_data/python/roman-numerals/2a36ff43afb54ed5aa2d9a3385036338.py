import unittest
'''
Roman numeral rules:
    A letter con only be replaced up to 3 times
        Example:
            XXX - 30
    If a letter is placed before of a one of greater value, subtract it
        Exapmle:
            IX - 9
        Subtract only by powers of 10 I,X,C,M
            Example:
                41 - XLI
        Do not subtract a number from one that is more than 10 times greater
            Example:
                49 - XLIX not IL
'''

_arabic = [1000,500,100,50,10,5,1]
_roman = ["M","D","C","L","X","V","I"]

def numeral(arabic):
    roman = ""
    power = 2   # 10**2 = 100
    indpower = 2    # position of 100 in the array 
    for index in range(len(_arabic)):
        if 10**power>=_arabic[index] and _arabic[index]!=1:
            power -= 1
            indpower = _arabic.index(10**power)
        while arabic>=_arabic[index]:
            arabic -= _arabic[index]
            roman += _roman[index]
        if arabic!=0 and arabic>=(_arabic[index]-10**power):
            arabic -= (_arabic[index]-10**power)
            roman += _roman[indpower] + _roman[index]
    return roman
        
            
            
            
            
