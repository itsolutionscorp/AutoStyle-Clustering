class Binary
	def initialize(num)
		@num = num
	end

	def to_binary
		array = []
		return 0 if is_num? == 0
		until @num == 0
			array << @num%2
			@num = @num/2
		end
		array.reverse.join
	end

	def to_decimal
		return 0 if is_num? == 0
		sums = []
		array = @num.split('').reverse
			array.each_with_index do |n,index|
				sums << (n.to_i)*2**index
			end
		sums.inject(:+)
	end

	def is_num?
		@num.to_i
	end
end

Binary.new('1001').to_decimal
