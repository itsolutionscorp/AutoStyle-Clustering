module Raindrops

	def self.convert(number)
		num = Number.new(number)

		if num.primes.empty?
			return num.number.to_s
		else
			return num.primes
		end
	
	end

end

class Number 
	attr_reader :number

	def initialize(number)
		@number = number
		@primes = primes
	end

	def primes
		ary = []
		ary << 'Pling' if prime_factor_of_3?
		ary << 'Plang' if prime_factor_of_5?
		ary << 'Plong' if prime_factor_of_7?
		ary.join('')
	end

	private 

	def prime_factor_of_3?
		number % 3 == 0
	end

	def prime_factor_of_5?
		number % 5 == 0
	end

	def prime_factor_of_7?
		number % 7 == 0
	end
end