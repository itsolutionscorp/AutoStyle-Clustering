def sieve(end_number):
    list_of_numbers = range(2,end_number+1)
    index = 0
    while index < len(list_of_numbers):
        list_of_numbers = list_of_numbers[:index+1] + [x for x in list_of_numbers[index:] if x % list_of_numbers[index] != 0]
        index+=1
    return list_of_numbers
    
