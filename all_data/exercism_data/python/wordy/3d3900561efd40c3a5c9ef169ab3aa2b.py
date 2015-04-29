import operator
signs = {
    'plus':operator.add,
    'minus' : operator.sub,
    'multiplied' : operator.mul,
    'divided': operator.floordiv}
    
def calculate(what):
    lst = what.strip('?').split()
    flag = True
    last_value = ''
    for cnt,i in enumerate(lst):
            #checking if its a negative or positive number
            if i.isdigit() or i[1:].isdigit():
                #checking if its the first number or if it comes after an operator
                if last_value in signs or last_value == '':
                    #the first number gets appointed to sum
                    if flag:
                        suma = int(i)
                        flag = False
                    last_value = i
                else:
                    raise ValueError
            elif i in signs:
                #multiplied by and divided by need an extra case
                if lst[cnt+1] == 'by':
                    suma = signs[i](suma,int(lst[cnt+2]))
                else:
                    suma = signs[i](suma,int(lst[cnt+1]))
                last_value = i
    return suma
