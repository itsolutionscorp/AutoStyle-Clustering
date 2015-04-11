class Raindrops
	def self.convert(number)
		output = ""
		number % 3 == 0 ? output << "Pling" : output << ""
		number % 5 == 0 ? output << "Plang" : output << ""
		number % 7 == 0 ? output << "Plong" : output << ""
		number % 3 != 0 && number % 5 != 0 && number % 7 != 0 ? output << number.to_s : output << ""
	end
end
