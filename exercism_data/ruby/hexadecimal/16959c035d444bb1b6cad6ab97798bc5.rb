class Hexadecimal
	def initialize (hex)
		hex =~ /[^0-9a-f]/ ? @hex = nil : @hex = hex.chars.reverse	
	end

	def to_decimal
		return 0 if @hex ==nil
		sum = 0
		@hex.each_with_index {|char, i| sum += char.hex * 16 ** i}
		sum
	end
end
