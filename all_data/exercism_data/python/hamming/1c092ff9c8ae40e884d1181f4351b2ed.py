import operator

def distance(arg1,arg2):
    if len(arg1) == len(arg2):
        #operator.ne is the equivalent of != but can be passed in map
        #sum adds all the non matching characters
        return sum(map(operator.ne, arg1, arg2))
        
    else:
        return 'Mayday, mayday'
