class Raindrops
	def self.convert(number)
		rtn_string = ""
		
		if (number % 3 == 0)
			rtn_string << "Pling"
		end

		if (number % 5 == 0)
			rtn_string << "Plang"
		end

		if (number % 7 == 0)
			rtn_string << "Plong"
		end

		if rtn_string == ""
			rtn_string = number.to_s
		end
		
		return rtn_string
	end
end
