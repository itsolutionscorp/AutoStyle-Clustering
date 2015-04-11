class Trinary
	def initialize tri
		@num = _parse tri
	end

	def to_decimal
		@num
	end

	def _parse tri
		sum = 0
		tri.chars.reverse.each_with_index {|digit, position|
			position_value = position == 0 ? 1 : 3**position

			sum += digit.to_i * position_value
		}
		sum
	end
end
