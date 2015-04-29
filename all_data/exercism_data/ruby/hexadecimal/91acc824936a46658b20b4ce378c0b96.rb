class Hexadecimal	

	def initialize(hexadecimal_string)
		@hexadecimal_string = hexadecimal_string	
	end
	
	def to_decimal
		if /[^0-9a-f]/.match(@hexadecimal_string) != nil
				result = 0
		else
			reverse_string = @hexadecimal_string.reverse
			arr = reverse_string.split(//)
			arr_int = arr.map do |element| 
				case element
					when 'a'
						10
					when 'b'
						11
					when 'c'
						12
					when 'd'
						13
					when 'e'
						14
					when 'f'
						15	
					else 				
						element.to_i
				end
			end
			# puts arr_int
			result = 0
			for i in 0..(arr_int.size - 1)
				result += arr_int[i] * 16 ** i
			end
		end
		result
	end

end
