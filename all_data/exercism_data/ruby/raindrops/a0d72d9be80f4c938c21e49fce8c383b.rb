class Raindrops
	def self.convert optimus_prime
		
		prime_zero = optimus_prime / optimus_prime
		prime_three = optimus_prime % 3
		prime_five = optimus_prime % 5
		prime_seven = optimus_prime % 7

		primo = []

		pling = 'Pling'
		plang = 'Plang'
		plong = 'Plong'
		
		if prime_three == 0
			primo.push (pling)
		end
		
		if prime_five == 0
			primo.push (plang)
		end
		
		if prime_seven == 0
			primo.push (plong)	
		end

		if primo.any? == true
			return primo.join
		end

		if primo.any? == false	
			return optimus_prime.to_s
		end
	end
end
