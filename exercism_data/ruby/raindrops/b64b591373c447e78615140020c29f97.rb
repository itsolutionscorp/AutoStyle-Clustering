class Raindrops
	def self.convert(number)
		res = prime_factor(number, 3, "Pling")
		res += prime_factor(number, 5, "Plang")
		res += prime_factor(number, 7, "Plong")
		(res.empty?) ? number.to_s : res
	end

	def self.prime_factor(number, divisor, str) 
		(number % divisor == 0) ? str : ""
	end
end
