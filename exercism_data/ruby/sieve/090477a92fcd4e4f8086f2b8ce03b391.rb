class Fixnum
	def multiple_of(n)
		self.modulo(n) == 0
	end
end

class Sieve
	def initialize(limit)
		@limit = limit
	end
	
	def primes
		@primes || sieve
	end
	
private
	def sieve
		list = (2..@limit).to_a
		selected = []
		until list.empty? do
			selected << list.first
			list.reject! { |n| n.multiple_of(selected.last) }
		end
		selected
	end
end
