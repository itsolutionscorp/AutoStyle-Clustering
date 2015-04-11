class SumOfMultiples
	def initialize(*args)
		@multiples = args
	end

	def to(max_num)
		numbers = Array.new(max_num,0)
		numbers.each_index do |i|
			@multiples.each do |multiple|
				if i % multiple == 0 then
					numbers[i] = i
				end
			end
		end
		result = 0
		numbers.each do |num|
			result += num
		end
		result
	end

	def self.to(max_num)
		SumOfMultiples.new(3,5).to(max_num)
	end
end
