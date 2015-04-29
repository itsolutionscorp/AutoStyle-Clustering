class Bob
	def hey(a)
		last = a[-1,1]

		if last == '.'
			'Whatever.'
		elsif last == '!'
			'Woah, chill out!'
		elsif last == '?'
			'Sure.'
		else
			'Fine. Be that way!'
		end
	end	
end
