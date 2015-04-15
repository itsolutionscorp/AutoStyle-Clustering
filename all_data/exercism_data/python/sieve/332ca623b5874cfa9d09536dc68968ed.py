def sieve(number):
    number_list = range(2,number+1)
    index = 0
    while number_list[index]**2 <= number_list[-1]:
        for value in range(number_list[index]**2,number+1,number_list[index]):
            if value in number_list:
                number_list.remove(value)
        index += 1
    return number_list
        
            
