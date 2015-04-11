class Bob

	def hey(msg)
		if msg.to_s.strip.empty?
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
