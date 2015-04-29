class Trinary
	
	def initialize(trits)
		@trits = trits =~ /^[012]+$/ ? trits : "0"
	end

	def to_decimal
		@trits.each_char.with_index.reduce(0) do |dec, (b, i)| 
			dec += b.to_i*3**(@trits.size-i-1)
		end
	end
end
