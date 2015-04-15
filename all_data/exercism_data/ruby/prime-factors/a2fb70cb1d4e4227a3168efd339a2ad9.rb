class PrimeFactors
	def self.for(number)
		return [] if number == 1
		array =*(2..number)
		factors = array.select{ |x| number % x==0 }.inject([]){ |x,y|
			x << y
			x << number / y unless y == number / y
			x 
		}.uniq.sort[1..-1]

# TODO Change case statements and include Prime class
 
		factors.map do |x|
			case
			when x == 2 || x % 2 == 0 
				2
			when x == 3 || x % 3 == 0
				3
			when x == 5 || x % 5 == 0
				5
			else
				fail "Primes only go up to 5"
			end
		end
	end
end
