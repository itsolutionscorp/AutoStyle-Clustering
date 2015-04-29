class Raindrops
	require 'prime'
	def self.convert(number)
		primes = number.prime_division.map { |prime,power| prime }
		response = ""
		response += "Pling" if primes.include?(3)
		response += "Plang" if primes.include?(5)
		response += "Plong" if primes.include?(7)
		response += number.to_s if response.empty?

		response
	end
end
