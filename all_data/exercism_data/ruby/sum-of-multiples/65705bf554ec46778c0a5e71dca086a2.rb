class SumOfMultiples

	def initialize(*multiples_of)
		multiples_of.empty? ? @multiples_of = [3, 5] : @multiples_of = multiples_of
	end
	
	def to(number)
		arr = Array(1...number)
		sum_of_multiples = []
		multiples_of = @multiples_of
		arr.each do |n|
			multiples_of.each do |m|
				sum_of_multiples << n if n % m == 0
			end
		end
		if sum_of_multiples.uniq.inject(:+) == nil
			return 0
		else
			return sum_of_multiples.uniq.inject(:+)
		end
	end
	
	def self.to(number)
		SumOfMultiples.new.to(number)
	end

end
