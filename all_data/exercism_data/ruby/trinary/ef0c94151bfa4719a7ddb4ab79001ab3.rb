class Trinary

	def initialize(num)

		@num = num

	end

	def to_decimal
		num_array = @num.split("").map { |num| num.to_i }
		num_array.reverse!
		decimal = 0
		pow = 0
		num_array.each do |tit|
			decimal += tit*(3**pow)
			pow+=1
		end
		decimal
	end
end
