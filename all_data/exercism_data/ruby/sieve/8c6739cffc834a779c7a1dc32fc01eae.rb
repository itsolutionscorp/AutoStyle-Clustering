class Sieve

	def initialize(limit)
		@limit = limit
		@list = create_list(limit)
	end

	def create_list(limit)
		list = Array.new

		2.upto(limit) do |n|
			list << n
		end

		return list
	end

	def primes
		find_primes(2)
		return @list
	end

	def find_primes(start)
		delete_multiples(start)
		bigger = 0
		@list.each do |i|
			if i > start
				bigger = i
				break
			end
		end

		if bigger != 0
			find_primes(bigger)
		end
	end

	def delete_multiples(start)
		i = 2
		until (start * i > @limit) do
			@list.delete(start * i)
			i += 1
		end
	end

end
