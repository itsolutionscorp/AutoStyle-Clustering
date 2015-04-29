class Fixnum
	def to_roman
			rome = self

			roman_loop = []

			rome_nums = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 1]

			rome_m = (rome/rome_nums[0])
			rome -= rome_m * 1000
			rome_nine_hundred = (rome/rome_nums[1])
			rome -= rome_nine_hundred * 900
			rome_d = (rome/rome_nums[2])
			rome -= rome_d * 500
			rome_four_hundred = (rome/rome_nums[3])
			rome -= rome_four_hundred * 400
			rome_c = (rome/rome_nums[4])
			rome -= rome_c * 100
			rome_ninety = (rome/rome_nums[5])
			rome -= rome_ninety * 90
			rome_l = (rome/rome_nums[6])
			rome -= rome_l * 50
			rome_forty = (rome/rome_nums[7])
			rome -= rome_forty * 40
			rome_x = (rome/rome_nums[8])
			rome -= rome_x * 10
			rome_niner = (rome/rome_nums[9])
			rome -= rome_niner * 9
			rome_v = (rome/rome_nums[10])
			rome -= rome_v * 5
			rome_i = (rome/rome_nums.last)

			# 1000 = "M"
			rome_m < 10
			thousands = "M" * rome_m
			roman_loop.push(thousands)
			# 900 = "CM"
			if rome_nine_hundred == 1
			roman_loop.push("CM")
			end
			# 500 = "D"
			rome_d < 2
			five_hundreds = "D" * rome_d
			roman_loop.push(five_hundreds)
			# 400 = "CL"
			if rome_four_hundred == 1
			roman_loop.push("CD")
			end
			# 100 = "C"
			rome_c < 5
			hundreds = "C" * rome_c
			roman_loop.push(hundreds)
			#90 = "XC"
			if rome_ninety == 1
			roman_loop.push("XC")
			end
			# 50 = "L"
			rome_l < 2
			fifties = "L" * rome_l
			roman_loop.push(fifties)
			# 40 = "XL"
			if rome_forty == 1
			roman_loop.push("XL")
			end
			# 10 = "X"
			rome_x < 4
			tens = "X" * rome_x
			roman_loop.push(tens)
			# 9 = "IX"
			if rome_niner == 1
			roman_loop.push("IX")
			end
			# 5 = "V"
			rome_v < 2
			fives = "V" * rome_v
			roman_loop.push(fives)
			# 4 = "IV"
			if rome_i < 4
			ones = "I" * rome_i
			roman_loop.push(ones)
			elsif rome_i == 4
			roman_loop.push("IV")
			end
			roman_loop.join
	end
end
