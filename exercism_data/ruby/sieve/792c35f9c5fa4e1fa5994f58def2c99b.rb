class Sieve
	attr_accessor :num_list, :prime_list

	def initialize (num)
		@num = num
		@num_list = (2..num).to_a
		@prime_list = []
	end

	def primes
		while !num_list.empty?
			var = num_list.shift
			prime_list << var
			var2 = var * 2
			while var2 <= @num
				num_list.delete(var2)
				var2 += var
			end
		end
		prime_list
	end

end
