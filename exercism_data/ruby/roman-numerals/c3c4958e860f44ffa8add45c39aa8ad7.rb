class Integer

	def to_roman
		number = self.to_s.split("").reverse
		roman = ""

		case self.to_s.length
		when 4
			roman << {"1" => "M", "2" => "MM", "3" => "MMM"}[number[3]]
			roman << {"1" => "C", "2" => "CC", "3" => "CCC", "4" => "CD", "5" => "D", "6" => "DC", "7" => "DCC", "8" => "DCCC", "9" => "CM", "0" => ""}[number [2]]
			roman << {"1" => "X", "2" => "XX", "3" => "XXX", "4" => "XL", "5" => "L", "6" => "LX", "7" => "LXX", "8" => "LXXX", "9" => "XC", "0" => ""}[number[1]]
			roman << {"1" => "I", "2" => "II", "3" => "III", "4" => "IV", "5" => "V", "6" => "VI", "7" => "VII", "8" => "VIII", "9" => "IX", "0" => ""}[number[0]]
		when 3
			roman << {"1" => "C", "2" => "CC", "3" => "CCC", "4" => "CD", "5" => "D", "6" => "DC", "7" => "DCC", "8" => "DCCC", "9" => "CM", "0" => ""}[number [2]]
			roman << {"1" => "X", "2" => "XX", "3" => "XXX", "4" => "XL", "5" => "L", "6" => "LX", "7" => "LXX", "8" => "LXXX", "9" => "XC", "0" => ""}[number[1]]
			roman << {"1" => "I", "2" => "II", "3" => "III", "4" => "IV", "5" => "V", "6" => "VI", "7" => "VII", "8" => "VIII", "9" => "IX", "0" => ""}[number[0]]
		when 2
			roman << {"1" => "X", "2" => "XX", "3" => "XXX", "4" => "XL", "5" => "L", "6" => "LX", "7" => "LXX", "8" => "LXXX", "9" => "XC", "0" => ""}[number[1]]
			roman << {"1" => "I", "2" => "II", "3" => "III", "4" => "IV", "5" => "V", "6" => "VI", "7" => "VII", "8" => "VIII", "9" => "IX", "0" => ""}[number[0]]
		else
			roman << {"1" => "I", "2" => "II", "3" => "III", "4" => "IV", "5" => "V", "6" => "VI", "7" => "VII", "8" => "VIII", "9" => "IX", "0" => ""}[number[0]]
		end
	end

end
