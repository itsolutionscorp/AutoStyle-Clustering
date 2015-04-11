class Raindrops

	def self.convert(num)
		sound = ""
		sound << "Pling" if num % 3 == 0

		return sound
	end
end
