class Binary
	def initialize(binary)
		@binary = binary
	end

	def to_decimal
		decimal = 0
		
		count = @binary.length
		@binary.split('').each do |bin|
			multiplier = 1

			(count-1).downto(1) do |iter|
				multiplier = multiplier * 2
			end
			count = count - 1
			
      decimal = decimal + (bin.to_i)*multiplier
      
		end
		return decimal
  end
end
