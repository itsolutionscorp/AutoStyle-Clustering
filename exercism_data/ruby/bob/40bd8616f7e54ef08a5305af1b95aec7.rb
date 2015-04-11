class Bob

	def initialize
	end

	def hey(msg)
		if msg.strip.empty?
			return 'Fine. Be that way!'
		elsif msg.upcase == msg && msg.downcase != msg
			return 'Woah, chill out!'
		elsif msg[-1, 1] == '?'
			return 'Sure.'
		else
			return 'Whatever.'
		end
	end

end
