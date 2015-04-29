class Raindrops
	def self.convert(n)
		str = ""

		str += "Pling" if n % 3 == 0
		str += "Plang" if n % 5 == 0
		str += "Plong" if n % 7 == 0

		if str == ""
			n.to_s
		else
			str
		end
	end

end
