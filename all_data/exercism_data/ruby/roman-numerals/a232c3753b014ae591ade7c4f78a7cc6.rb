class Integer
	def to_roman
		result = ''
		num = self
		while num >= 1000 do
			result << 'M'
			num -= 1000
		end
		if num >= 900
			result << 'CM'
			num -= 900
		elsif num >= 500
			result << 'D'
			num -= 500
		elsif num >= 400
			result << 'CD'
			num -= 400
		end				
		while num >= 100 do
			result << 'C'
			num -= 100
		end
		if num >= 90
			result << 'XC'
			num -= 90
		elsif num >= 50
			result << 'L'
			num -= 50
		elsif num >= 40
			result << 'XL'
			num -= 40
		end
		while num >= 10
			result << "X"
			num -= 10
		end
		if num == 9
			result << 'IX'
			num -= 9
		elsif num >= 5
			result << 'V'
			num -= 5
		elsif num == 4
			result << 'IV'
			num -=4
		end
		while num > 0
			result <<'I'
			num -= 1
		end

		return result
	end
end
				
										
