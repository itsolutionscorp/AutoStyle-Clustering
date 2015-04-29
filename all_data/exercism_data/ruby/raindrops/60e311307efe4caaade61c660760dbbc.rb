class Raindrops
	def self.convert(num)
		# See if number is divisable by primes 3, 5 and 7 without a remainder.
		# If the number isn't divisable, return it in string.
		string = ""

		string << "Pling" if num % 3 == 0
		string << "Plang" if num % 5 == 0
		string << "Plong" if num % 7 == 0

		string.empty? ? num.to_s : string
	end
end
