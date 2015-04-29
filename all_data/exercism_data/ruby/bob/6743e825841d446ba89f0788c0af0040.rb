class Bob	

	def hey(speech)
		if speech.strip.empty?
	  	return 'Fine. Be that way!'
	  elsif speech == speech.upcase
	  	return 'Woah, chill out!'
	  elsif speech.end_with?("?")
	  	return 'Sure.'
	  else
	  	return 'Whatever.'
	  end
	end

end 
