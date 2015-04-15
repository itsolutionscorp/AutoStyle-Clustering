class Binary

	
	def initialize(binary_str)
		@binary_num = /^[01]/ =~ binary_str ? binary_str : "0"
	end

	def to_decimal
		decimal = 0
		power = @binary_num.length - 1

		@binary_num.split("").each do |i|
			decimal += i.to_i * 2**power
			power -= 1
		end

		decimal
	end
end
