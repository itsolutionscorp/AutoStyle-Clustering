#require 'pry'

class Fixnum
	def to_roman
		
		num = self.to_s
		num_array = num.split("")
		while num_array.length < 4
			num_array.unshift("0")
		end
		
		output_roman = ""
		case num_array[-4]
		when "1"
			output_roman << 'M'
		when "2"
			output_roman << 'MM'
		when "3"
			output_roman << 'MMM'
		when "4"
			output_roman << 'MV'
		when "5"
			output_roman << 'V'
		when "6"
			output_roman << 'VM'
		when "7"
			output_roman << 'VMM'
		when "8"
			output_roman << 'VMMM'
		when "9"
			output_roman << 'MX'
		end


		case num_array[-3]
		when "1"
			output_roman << 'C'
		when "2"
			output_roman << 'CC'
		when "3"
			output_roman << 'CCC'
		when "4"
			output_roman << 'CD'
		when "5"
			output_roman << 'D'
		when "6"
			output_roman << 'DC'
		when "7"
			output_roman << 'DCC'
		when "8"
			output_roman << 'DCC'
		when "9"
			output_roman << 'CM'
		end


		case num_array[-2]
		when "1"
			output_roman << 'X'
		when "2"
			output_roman << 'XX'
		when "3"
			output_roman << 'XXX'
		when "4"
			output_roman << 'XL'
		when "5"
			output_roman << 'L'
		when "6"
			output_roman << 'LX'
		when "7"
			output_roman << 'LXX'
		when "8"
			output_roman << 'LXXX'
		when "9"
			output_roman << 'XC'
		end


		case num_array[-1]
		when "1"
			output_roman <<'I'
		when "2"
			output_roman << 'II'
		when "3"
			output_roman <<'III'
		when "4"
			output_roman <<'IV'
		when "5"
			output_roman <<'V'
		when "6"
			output_roman <<'VI'
		when "7"
			output_roman <<'VII'
		when "8"
			output_roman <<'VIII'
		when "9" 
			output_roman << 'IX'
		end


		output_roman
	end
end

#binding.pry
