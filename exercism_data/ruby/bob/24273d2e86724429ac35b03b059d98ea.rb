class Bob
	
	def hey(sentence)
		case 
		when sentence.nil? || sentence == ''
			then 'Fine. Be that way.'
		when sentence[-1] == '?'
			then 'Sure.'
		when sentence == sentence.upcase
			then 'Woah, chill out!'
		else 'Whatever.'
		end
	end

end
