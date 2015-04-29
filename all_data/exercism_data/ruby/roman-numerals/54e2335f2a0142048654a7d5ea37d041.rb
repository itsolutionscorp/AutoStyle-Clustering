	class Fixnum
		# M - 1,000
		# D - 500
		# C - 100
		# L - 50
		# X - 10
		# V - 5
		# I - 1
		def to_roman
			roman_numerals = { M: [1,4], D: [5,3], C: [1,3], L: [5,2], X: [1,2], V: [5,1], I: [1,1] }
			number_array = self.to_s.split('')
			roman = ''
			
			for i in 1..number_array.length
				curr_num = number_array.pop.to_i
				
				case curr_num
					when 1, 2, 3 
						roman_temp = roman_numerals.key([1,i]).to_s*curr_num
					when 4
						roman_temp = roman_numerals.key([1,i]).to_s + roman_numerals.key([5,i]).to_s
					when 5, 6, 7, 8
						roman_temp = roman_numerals.key([5,i]).to_s + roman_numerals.key([1,i]).to_s*(curr_num-5)
					when 9
						roman_temp = roman_numerals.key([1,i]).to_s + roman_numerals.key([1,i+1]).to_s
					else
						roman_temp = ''
				end
				
				roman = roman_temp + roman
			end
			
			return roman
		end
	end
