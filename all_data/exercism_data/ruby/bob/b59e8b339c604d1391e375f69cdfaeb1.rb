class Bob

	def hey(text)
		case 
		when (/[A-Za-z0-9]/ =~ text).nil? 
			'Fine. Be that way!'	
		when text == text.upcase && !(/[A-z]/ =~ text).nil?
			'Whoa, chill out!'
		when text.to_s.end_with?('?')
			'Sure.'
		else
			"Whatever."	
		end
	end

end
