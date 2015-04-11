class SumOfMultiples
	def initialize(*factors)
		@factors = factors
	end

	def self.to(upto)
		SumOfMultiples.new(3, 5).to(upto)
	end

	def to(upto)
		@upto = upto
		sum all_multiples
	end

	:private

	def all_multiples
		@factors.each_with_object([]){ |f, res| res.concat(multiples_for(f)) }.uniq
	end

	def multiples_for(factor)
		(factor..@upto - 1).step(factor).to_a
	end

	def sum(numbers)
		numbers == [] ? 0 : numbers.inject { |s, i| s + i }
	end
end
