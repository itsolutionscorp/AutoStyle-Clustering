class Bob
	def hey (something)
		
		if something.upcase == something && something =~ /[a-zA-Z]/
			'Whoa, chill out!'
		elsif something.count('?') > 0 && something.index('?') == (something.length - 1)
			'Sure.'
		elsif something.strip.empty?
			'Fine. Be that way!'
		else
			'Whatever.'
		end
	
	end
end
