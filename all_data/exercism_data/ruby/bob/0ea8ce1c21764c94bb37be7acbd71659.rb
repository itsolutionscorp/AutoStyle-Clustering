
class Bob
	def hey(in_str)
		if (in_str !~ /[a-z]/) and (in_str =~ /[A-Z]/)
			'Woah, chill out!'
		elsif in_str == ""
			'Fine. Be that way!'
		elsif in_str =~ /^\s+$/
			'Fine. Be that way!'
		elsif (in_str !~ /\n./ and in_str =~ /\?$/)
			'Sure.'	
	    else
	    	'Whatever.'
	    end
    end
end
