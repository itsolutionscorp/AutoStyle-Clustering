import operator,functools

def largest_product(what,number):
    if not what or number ==0:
        return 1
    list_of_products = []
    #for each slice it multiplies all it's members for a greater product
    #functools.reduce() (1,2,3) --> ((1,2),3)
    #lambda is commonly used with reduce
    for series in slices(what,number):
        list_of_products.append(functools.reduce(lambda x,y:x*y,series))
    return max(list_of_products)

    
def slices(what,number):
    
    if number>len(what):
        raise ValueError
    list_of_slices =[]
    #make the string a list of ints for easier manipulation
    what_sliced = [int(i) for i in what]
    #it slices the string in pieces of len(number)
    for i,j in enumerate(what_sliced):
        if i<len(what_sliced)-(number-1):
            list_of_slices.append(what_sliced[i:i+number:])
    return list_of_slices
