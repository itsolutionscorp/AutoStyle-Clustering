class Raindrops

	PRIMES = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
	
	def self.convert(number)
		output = ""
		PRIMES.each { |prime, noise| output << noise if number % prime == 0 }
		output << number.to_s if output.empty?
		return output
	end
end
