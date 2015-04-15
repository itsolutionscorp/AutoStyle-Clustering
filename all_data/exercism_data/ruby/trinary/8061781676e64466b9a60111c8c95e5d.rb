class Trinary
	VALID_CHARS = ["0","1","2"]
	def initialize(trinary)
		@trinary = trinary
	end

	def to_decimal
		present = false
		VALID_CHARS.each do |char|
			if @trinary.split('').include? char
				present = true
      end

		end
    unless present
    	return 0
    end
		decimal = 0
		
		count = @trinary.length
		@trinary.split('').each do |bin|
			multiplier = 1

			(count-1).downto(1) do |iter|
				multiplier = multiplier * 3
			end
			count = count - 1
			
      decimal = decimal + (bin.to_i)*multiplier
      
		end
		return decimal
  end
end
