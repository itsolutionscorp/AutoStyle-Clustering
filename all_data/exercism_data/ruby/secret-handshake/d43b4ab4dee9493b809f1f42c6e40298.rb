class SecretHandshake
	COMMANDS = ["wink", "double blink", "close your eyes", "jump"]
	def initialize(value)
		@bits = ("%5b" % value.to_i).reverse.chars.map(&:to_i)
	end
	
	def commands
		list = @bits[0..-2].zip(COMMANDS).select { |pair| pair.first == 1 }.map(&:last)
		reverse_handshake? ? list.reverse : list
	end

private
	def reverse_handshake?
		@bits.last == 1
	end
end
