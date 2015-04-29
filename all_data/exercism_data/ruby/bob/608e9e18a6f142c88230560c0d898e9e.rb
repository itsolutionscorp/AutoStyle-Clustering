class Bob
	def hey(greeting)
		greeting = greeting.to_s.strip
		if greeting.empty?
			return 'Fine. Be that way!'
		elsif greeting.upcase == greeting
			return 'Woah, chill out!'
		elsif greeting.chars.last == '?'
			return 'Sure.'
		end
		return 'Whatever.'
	end
end
