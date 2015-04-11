class Raindrops
	def self.convert optimus_prime

		primo = find_shit(optimus_prime)

		if primo.any?
			primo.join
		else
			optimus_prime.to_s
		end
	end

	def self.find_shit(optimus_prime)
		prime_three = optimus_prime % 3
		prime_five = optimus_prime % 5
		prime_seven = optimus_prime % 7

		primo = []

		primo.push ('Pling') if prime_three.zero?

		primo.push ('Plang') if prime_five.zero?

		primo.push ('Plong') if prime_seven.zero?

		primo
	end
end
