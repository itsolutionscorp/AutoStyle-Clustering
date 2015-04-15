class Hexadecimal
	
	attr_reader :hex


	def initialize(hex)
		@hex =  valid?(hex) ? hex.downcase : "0"
	end

	def to_decimal
		hex.chars.each_with_index.reduce(0) do |sum, (char, i)|
			sum + ( valueOf(char) * powerOf16(i) )
		end
	end

	private

		def valid?(hex)
			hex =~ /^[0-9a-fA-F]+$/
		end

		def valueOf(char)
			char =~ /\d/ ? char.to_i : (char.ord-97+10)
		end

		def powerOf16(i)
			16**(hex.chars.length-i-1)
		end

end
