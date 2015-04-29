
class Bob
	def hey(talk_to_Bob)
		if talk_to_Bob !~ /[a-z]/ and talk_to_Bob =~ /[A-Z]/
			'Woah, chill out!'
		elsif talk_to_Bob == ""
			'Fine. Be that way!'
		elsif talk_to_Bob =~ /^\s+$/
			'Fine. Be that way!'
		elsif talk_to_Bob !~ /\n./ and talk_to_Bob =~ /\?$/
			'Sure.'	
	    else
	    	'Whatever.'
	    end
    end
end
