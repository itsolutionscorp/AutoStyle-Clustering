roman_dict = {1: "I", 5: "V", 10: "X", 50: "L", 100: "C", 500: "D", 1000: "M"}
roman_list = [1000,500,100,50,10,5,1]

def numeral(num):
	roman_out = ""
	for i,j in enumerate(roman_list):
		count = 0
		while num >= j:
			count += 1

			if count == 4 and roman_dict[roman_list[i-1]] in roman_out:
				roman_out = roman_out[:-4] + roman_dict[j] + roman_dict[roman_list[i-2]]

			elif count == 4:
				roman_out = roman_out[:-3] + roman_dict[j] + roman_dict[roman_list[i-1]]

			else:
				roman_out += roman_dict[j]

			num -= j
	return roman_out
