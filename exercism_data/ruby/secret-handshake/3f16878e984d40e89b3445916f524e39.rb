class SecretHandshake
	attr_reader :commands

	def initialize(num)
		@secret_code = []
		if num.class == Fixnum
			@binary = num.to_s(2).rjust(5, "0000").chars.map(&:to_i)
		else
			@binary = []
		end
	end

	def commands
		reverse = set_reverse?
		@binary.each_with_index do |binary, index|
			@secret_code << encrypt[index] if binary == 1
		end
		reverse ? @secret_code : @secret_code.reverse
	end

	private

	def set_reverse?
		@binary.shift == 1
	end

	def encrypt
		[
			"jump", 						#1000
			"close your eyes",  #100
			"double blink",     #10
			"wink"              #1
		] 
	end

end
