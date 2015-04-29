class Raindrops

	def self.convert(num)
		str = ""
		str << "Pling" if num%3 == 0
		str << "Plang" if num%5 == 0
		str << "Plong" if num%7 == 0

		if str == ""
			return num.to_s
		else
		 	return str
		end
	end
end
