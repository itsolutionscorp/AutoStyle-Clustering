class Raindrops

	def self.convert(number)
		rainspeak =""
		rainspeak << "Pling" if (number % 3) == 0
		rainspeak << "Plang" if (number % 5) == 0
		rainspeak << "Plong" if (number % 7) == 0
		return number.to_s if rainspeak.size == 0
		rainspeak
	end
end
