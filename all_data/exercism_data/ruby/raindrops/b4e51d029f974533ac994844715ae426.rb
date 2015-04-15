class Raindrops
  def self.convert number
    primes = prime_factors number
    text = ""
    text += "Pling" if primes.include? 3
    text += "Plang" if primes.include? 5
    text += "Plong" if primes.include? 7
    return text unless text.empty?
    number.to_s
  end

	def self.prime_factors number
		primes = []
		candidate = 2
		while candidate <= number
			while number % candidate == 0
				primes << candidate
				number /= candidate
			end
			candidate += 1
		end
		primes
	end
end
