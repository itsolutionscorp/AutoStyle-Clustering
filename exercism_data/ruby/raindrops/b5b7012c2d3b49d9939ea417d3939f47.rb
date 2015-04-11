class Raindrops
	def self.convert(number_raindrops)
		output = ""
		output << "Pling" if is_prime_factor?(number_raindrops, 3)
		output << "Plang" if is_prime_factor?(number_raindrops, 5)
		output << "Plong" if is_prime_factor?(number_raindrops, 7)
		output.empty? ? number_raindrops.to_s : output
	end

	private
	def self.is_prime_factor?(number, factor)
		number % factor == 0
	end
end
