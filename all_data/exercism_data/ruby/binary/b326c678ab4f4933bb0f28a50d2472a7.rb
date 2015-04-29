class Binary
	
	def initialize(bits)
		@bits = bits =~ /^[01]+$/ ? bits : "0"
	end

	def to_decimal
		@bits.each_char.with_index.reduce(0) do |dec, (b, i)| 
			dec += b.to_i*2**(@bits.size-i-1)
		end
	end
end
