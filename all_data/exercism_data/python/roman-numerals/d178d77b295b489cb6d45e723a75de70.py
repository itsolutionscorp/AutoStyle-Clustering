# Roman Numerals

def numeral(number):
    roman_nums_list = (('M'), ('C', 'D'), ('X', 'L'), ('I', 'V'))
    
    result = []
    
    number_list = list(str(number))
    number_list = list(map(int, number_list))
    
    number_length = len(number_list)
    
    roman_index = 4 - number_length
    
    for i in range(number_length):
        current_number = number_list[i]
        roman_nums = roman_nums_list[roman_index]
        if roman_index == 0:
            result.append(roman_nums * current_number)
        
        else:
            if current_number < 4:
                result.append(current_number * roman_nums[0])
                
            elif current_number == 4:
                result.append(roman_nums[0])
                result.append(roman_nums[1])
                
                
            elif current_number < 9:
                result.append(roman_nums[1])
                result.append((current_number - 5) * roman_nums[0])
                
            else:
                result.append(roman_nums[0])
                result.append(roman_nums_list[roman_index -1][0])
                            
        roman_index += 1
        
    return "".join(result)
