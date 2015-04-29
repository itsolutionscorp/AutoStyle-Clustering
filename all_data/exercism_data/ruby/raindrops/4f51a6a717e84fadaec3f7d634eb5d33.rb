class Raindrops
	def self.convert(number)

		factor_string = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}
		rtn_string = ""

		factor_string.each {|factor, string|
			if (number % factor == 0)
				rtn_string << string
			end
		}

		if rtn_string == ""
			rtn_string = number.to_s
		end

		return rtn_string
	end
end
