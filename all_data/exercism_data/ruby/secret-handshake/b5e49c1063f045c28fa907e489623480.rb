class SecretHandshake
	attr_accessor :commands

	@@COMMANDS = ["wink", "double blink", "close your eyes", "jump", :reverse_commands]

	def initialize(n)
		@bin = valid?(n) ? n.to_s(2) : "0"
		determine_commands
	end

	private

		def determine_commands
			@commands = []
			@@COMMANDS.each_with_index do |command, i|
				if commandIsOn?(i)
					if command.is_a? String
						commands << command
					elsif command.is_a? Symbol
						send(command)
					end
				end
			end
		end

		def commandIsOn?(n)
			@bin.chars[-(n+1)] == "1"
		end

		def reverse_commands
			self.commands = self.commands.reverse
		end

		def valid?(n)
			n.is_a?(Integer) && n >= 0
		end
end
