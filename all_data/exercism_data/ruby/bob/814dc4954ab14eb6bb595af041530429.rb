class Bob
	
	def hey(question)
		question.strip!
		return 'Fine. Be that way!' if question.empty?
		return 'Woah, chill out!' if question== question.upcase && question=~ /[[:alpha:]]/
		return 'Sure.' if question[-1]=='?'
		return 'Whatever.'
	end


end
