class Bob
	def hey(msg)

		if msg.upcase == msg && msg.downcase != msg
			'Woah, chill out!'
		elsif msg.end_with? '?'
			'Sure.'
		elsif msg.strip.empty? 
			'Fine. Be that way!'
		else 'Whatever.'
		end
		

	end
end
