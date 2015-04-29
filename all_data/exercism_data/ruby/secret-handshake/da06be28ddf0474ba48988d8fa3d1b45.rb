class SecretHandshake

	def initialize(value)
		if value.is_a?(String)
			@base2 = value
		else
			@base2 = value.to_s(2)
		end
	end
	
	HANDSHAKE = ['wink', 'double blink', 'close your eyes', 'jump']

	def commands
		handshake = []
		arr = @base2.reverse.split(//)
		for i in 0..3 do
			handshake << HANDSHAKE[i] if arr[i] == '1'
		end
		handshake = handshake.reverse if @base2.to_i > 16
		handshake
	end

end
