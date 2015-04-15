class SecretHandshake
	attr_reader :commands

	@@moves = ['wink', 'double blink', 'close your eyes', 'jump']

	def initialize(binary)
		binary = binary.to_s(2) if binary.is_a?(Integer)
		binary = "0" if binary =~ /[^01]/
		binary.reverse!

		@commands = @@moves.zip(binary.chars).select{|p| p[1] == '1'}.collect{|p| p[0]}
		@commands.reverse! if binary[4]
	end
end
