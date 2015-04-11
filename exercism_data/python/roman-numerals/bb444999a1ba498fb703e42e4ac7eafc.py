__author__ = 'tracyrohlin'

def ten_digit_space(number):
    tens_list = []
    if int(number) < 4:
        tens_list.append("X" * int(number[0]))
    elif int(number) == 4:
        tens_list.append("XL")
    elif 5 <= int(number) <= 8:
        tens_list.append("L")
        tens_list.append("X" * (int(number[0])-5))
    else:
        tens_list.append("XC")
    return "".join(tens_list)

def hundreds_digit_space(number):
    hundreds_list = []
    if 1 <= int(number) < 4:
        hundreds_list.append("C" * (int(number)))
    elif int(number) == 4:
        hundreds_list.append("CD")
    elif int(number) == 5:
        hundreds_list.append("D")
    elif 5 < int(number) < 9:
        hundreds_list.append("D")
        hundreds_list.append("C" * (int(number[0])-5))
    else:
        hundreds_list.append("CM")
    return "".join(hundreds_list)

def thousands_digit_space(number):
    thousands_list = []
    if int(number)<4:
        thousands_list.append("M" * (int(number)))
    else:
        pass
    return "".join(thousands_list)


def numeral(number):
    string_num = str(number)
    roman_num = {"1": "I", "2": "II", "3": "III", "4": "IV", "5": "V",
                 "6": "VI", "7": "VII", "8": "VIII", "9": "IX"}
    if len(string_num) == 1:
        return roman_num[string_num]
    elif len(string_num) == 2:
        digit_list = []
        digit_list.append(ten_digit_space(string_num[0]))
        digit_list.append(roman_num[string_num[1]])
        return "".join(digit_list)
    elif len(string_num) == 3:
        digit_list = []
        digit_list.append(hundreds_digit_space(string_num[0]))
        if string_num[1] != "0":
            digit_list.append(ten_digit_space(string_num[1]))
        if string_num[2] != "0":
            digit_list.append(roman_num[(string_num[2])])
        return "".join(digit_list)
    else:
        digit_list = []
        digit_list.append(thousands_digit_space(string_num[0]))
        if string_num[1] != "0":
            digit_list.append(hundreds_digit_space(string_num[1]))
        if string_num[2] != "0":
            digit_list.append(ten_digit_space(string_num[2]))
        if string_num[3] != "0":
            digit_list.append(roman_num[string_num[3]])
        return "".join(digit_list)

print numeral(1995)
