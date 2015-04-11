class Integer

	@@roman_transcription = { 0 => {9 => "IX", 5 => "V", 4 => "IV", 1 => "I"},
	 						  1 => {9 => "XC", 5 => "L", 4 => "XL", 1 => "X"},
	   						  2 => {9 => "CM", 5 => "D", 4 => "CD", 1 => "C"},
	    					  3 => {1 => "M"}
	    					}

	def @@roman_transcription.number(value, index)		
		if self[index][value] == nil && value != 0			
			 "" << self.number(value - 1, index) << self[index][1]
		else
			self[index][value]
		end
	end

	def to_roman
		self.to_s.reverse.chars.each_with_index.inject("") { |acc, (value, index) | 			
			"#{@@roman_transcription.number(value.to_i, index)}#{acc}"		
		}		
	end

end
