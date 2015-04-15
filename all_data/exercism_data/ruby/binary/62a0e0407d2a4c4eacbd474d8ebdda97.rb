class Binary
	def initialize(bin)
		@bin = bin
	end

	def to_decimal
		power = -1
		dec = 0
		@bin.split('').reverse.each do |b|
			break if !b.match(/[01]/)
			power += 1
			dec += Integer(b) * 2**power
		end
		dec
	end
end
