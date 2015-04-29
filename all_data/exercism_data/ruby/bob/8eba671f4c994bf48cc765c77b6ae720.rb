class Bob
	def hey(prompt)
		case
		when prompt == prompt.upcase && prompt =~ /[a-zA-Z]/
			'Woah, chill out!'		
		when prompt[-1] == '?'
			'Sure.'
		when prompt.scan(/\s/).size == prompt.size
			'Fine. Be that way!'
		else 
			'Whatever.'
		end
	end
end
