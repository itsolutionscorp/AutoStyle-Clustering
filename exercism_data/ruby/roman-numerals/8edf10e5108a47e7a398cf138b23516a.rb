class Fixnum
	def to_roman
		num = self
		roman_num = ''
		(num/1000).times{roman_num += 'M'; num -= 1000} while num >= 1000
		
		(num/500).times{roman_num += 'D'; num -= 500} while num >= 500
		(num/100).times{roman_num += 'C'; num -= 100} while num >= 100
		roman_num.gsub!('DCCCC', 'CM')
		roman_num.gsub!('CCCC', 'CD')
		
		(num/50).times{roman_num += 'L'; num -= 50} while num >= 50
		(num/10).times{roman_num += 'X'; num -= 10} while num >= 10
		roman_num.gsub!('LXXXX', 'XC')
		roman_num.gsub!('XXXX', 'XL')
		
		(num/5).times{roman_num += 'V'; num -= 5} while num >= 5
		(num/1).times{roman_num += 'I'; num -= 1} while num > 0
		roman_num.gsub!('VIIII', 'IX' )
		roman_num.gsub!('IIII', 'IV')

		roman_num
	end

end
