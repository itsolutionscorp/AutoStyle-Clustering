class Fixnum 	
	def to_roman	
		number = self		
		val = ""

		roman_mapping.each do |arabic, roman|
			while number >= arabic
			val += roman
			number -= arabic
			end  
		end

		val
	end	
	
private

	def roman_mapping 
		{
			1000 => 'M',
			900 => 'CM',
			500 => 'D',
			400 => 'CD',
			100 => 'C',
			90 => 'XC',
			50 => 'L',
			40 => 'XL',
			10 => 'X',
			9 => 'IX',
			5 => 'V',
			4 => 'IV',
			1 => 'I'		,
		}
	end
end
