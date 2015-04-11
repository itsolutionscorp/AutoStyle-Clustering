class Octal
	VALID_CHARS = ["0","1","2","3","4","5","6","7"]
	def initialize(octal)
		@octal = octal
	end

	def to_decimal
		present = false
		VALID_CHARS.each do |char|
			if @octal.split('').include? char
				present = true
      end

		end
    unless present
    	return 0
    end
		decimal = 0
		
		count = @octal.length
		@octal.split('').each do |bin|
			multiplier = 1

			(count-1).downto(1) do |iter|
				multiplier = multiplier * 8
			end
			count = count - 1
			
      decimal = decimal + (bin.to_i)*multiplier
      
		end
		return decimal
  end
end
