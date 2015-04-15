class Trinary

	def initialize(string)
		@trinary = string
	end
	
	def to_decimal
		trinary_arr = @trinary.reverse.split(//)
		decimal = 0
		trinary_arr.each_with_index do |trinary, index|
			decimal += trinary.to_i * 3 ** index
		end
		decimal
	end
end



