class SumOfMultiples
	def initialize(*multiples)
		@multiples = multiples
	end

	def self.to(num, multiples=[3, 5])
		(0...num).reduce{|total, val| (multiples.any?{|m| val % m == 0}) ? total + val : total}
	end

	def to(num)
		SumOfMultiples.to(num, @multiples)
	end
end
