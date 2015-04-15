class Sieve

	def initialize num
		@num = num
	end

	def primes 
		range = (2..@num).to_a
		to_delete = []
		2.upto(@num) do |i|
			range.select do |j|
				range.delete(j) if j%i == 0 && j != i
			end
		end	
	range
	end
end
