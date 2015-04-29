class Raindrops
	def self.convert(number)
		raindrop = ""
		raindrop += "Pling" if is_factor?(3, number)
		raindrop += "Plang" if is_factor?(5, number)
		raindrop += "Plong" if is_factor?(7, number)
		raindrop += number.to_s if raindrop.empty?
		raindrop
	end

	def self.is_factor?(potential_factor, number)
		number % potential_factor == 0
	end
end
