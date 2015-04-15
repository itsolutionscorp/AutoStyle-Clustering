class Fixnum 

	def to_roman()
		value = self
		result = ""
		convertion = {'M'=> 1000, 'CM'=> 900, 'D'=> 500, 'CD'=> 400, 'C'=> 100, 'XC'=> 90, 'L'=> 50, 
									'XL'=> 40, 'X'=> 10, 'IX'=> 9, 'V'=>5, 'IV'=> 4, 'I'=> 1}
		while value > 0
			convertion.each do |x, y|
				if value >= y 
					result += x
					value -= y
					break
				end
			end
		end
		return result
	end
	
end
