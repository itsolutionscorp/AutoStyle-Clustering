class Binary

	attr_accessor :num

	def initialize num
		@num = num
	end

	def to_decimal
		decimal = 0
		return 0 if num.to_i == 0
		num.split("").reverse.each_with_index do |number, index| 
			decimal += number.to_i * 2 ** index
		end
		decimal
	end
	
end
