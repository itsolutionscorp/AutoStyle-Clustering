class Raindrops
	def self.convert(num)
		return num.to_s if num % 3 != 0 && num % 5 != 0 && num % 7 != 0

		 s = ""
		 s << "Pling" if num % 3 == 0
		 s << "Plang" if num % 5 == 0
		 s << "Plong" if num % 7 == 0
		 s
	end
end
