class Sieve
	def initialize(x)
		@range = (2..x).to_a
		@cap = x
	end

 	def primes
   		(0..(@range.index(Math.sqrt(@cap).to_i))).each do |i|
     		n = @range[i]
     		(i + n).step(@cap - 2, n) { |j| @range[j] = nil } unless n.nil?
   		end
   	@range.compact
 	end
end
