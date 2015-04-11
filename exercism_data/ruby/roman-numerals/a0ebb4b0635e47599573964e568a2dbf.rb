=begin
	File: roman.rb
	Author: sherinom
=end

class Fixnum
	
	RN = {
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
		1 => 'I'
	}
	
	def to_roman()
		
		roman = ''
		number = self
		
		if number > 0 && number <= 3000
			RN.each do |base, letter|
				while number >= base
					roman += letter
					number -= base
				end
			end
		end
		
		return roman
		
	end
	
end
