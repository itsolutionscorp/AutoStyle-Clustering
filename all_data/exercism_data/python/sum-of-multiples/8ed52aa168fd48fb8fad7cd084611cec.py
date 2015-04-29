#-------------------------------------------------------------------------------
# Name:        McFace3000
# Purpose:      To create sulci and gyri
#-------------------------------------------------------------------------------

def sum_of_multiples(one, myList = []):
    end = []
    duck = create_range(one)
    if len(myList) == 0:
        close = default(duck)
        return add_up(close)
    else:
        for i in myList:
            if i > 0:
                test = superman_sucks(duck, i)
                new = add_up(test)
                end.append(new)
        close = end
        return add_up(close)

def default(duck):
    numbers = [3,5]
    end = []
    for i in  numbers:
        test = superman_sucks(duck, i)
        new = add_up(test)
        end.append(new)
    return end

def superman_sucks(duck, x):
    x = [int(i) for i in duck if i % x == 0]
    return x

def add_up(newList = []):
    total = 0
    for i in newList:
        total += i
    return total

def create_range(variable):
    c_create = range(1, variable)
    return c_create
print create_range(10000)
