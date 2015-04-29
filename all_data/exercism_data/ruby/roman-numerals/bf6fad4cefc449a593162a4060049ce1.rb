class Integer

	def to_roman
		size = self.to_s.length
		string = ""
		array_of_self = self.to_s.split('')
		(0..size - 1 ).each do |position|
			number = determine_number(array_of_self, size, position)
			string = roman_numeral_append(number,array_of_self,position,string)
		end
		string
	end

	def roman_numeral_append(number, array_of_self,position,string)
		string += matchup[number] unless array_of_self[position].to_i == 0
		string
	end

	def determine_number(array_of_self, size, position)
		number = array_of_self[position].to_i * multiple(size,position)
	end

	def multiple(size, position)
		("1"+"0"*(size - 1 - position)).to_i
	end

	def matchup
		{
			1 => "I",
			2 => "II",
			3 => "III",
			4 => "IV",
			5 => "V",
			6 => "VI",
			7 => "VII",
			8 => "VIII",
			9 => "IX",
			10 => "X",
			20 => "XX",
			30 => "XXX",
			40 => "XL",
			50 => "L",
			60 => "LX",
			70 => "LXX",
			80 => "LXXX",
			90 => "XC",
			100 => "C",
			200 => "CC",
			300 => "CCC",
			400 => "CD",
			500 => "D",
			600 => "DC",
			700 => "DCC",
			800 => "DCCC",
			900 => "CM",
			1000 => "M",
			2000 => "MM",
			3000 => "MMM"
		}
	end

end
