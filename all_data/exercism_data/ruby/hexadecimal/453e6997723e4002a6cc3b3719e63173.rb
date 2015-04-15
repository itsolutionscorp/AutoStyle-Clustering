class Hexadecimal

	def initialize(value)
		@value = value
		@power = @value.size - 1
		@letters = {
			'a' => 10,
			'b' => 11,
			'c' => 12,
			'd' => 13,
			'e' => 14,
			'f' => 15
		}
	end

	def to_decimal
		return 0 if @value =~ /[^abcdef\d]/

		decimal = 0
		@value.split('').each_with_index do |chr, i|
			if @letters.keys.include? chr
				decimal += @letters[chr] * 16**(@power - i)
			else
				decimal += chr.to_i * 16**(@power - i)
			end
		end
		decimal
	end

end
