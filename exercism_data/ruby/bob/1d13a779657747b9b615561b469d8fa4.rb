class Bob
	def hey(message) 
		anwser = if is_empty?(message)
			"Fine. Be that way!"	
		elsif is_question?(message)
			"Sure."
		elsif is_upcase?(message)
			"Woah, chill out!"
		else
			"Whatever."
		end
	end
 
	def is_upcase?(string)
    	!string[/[[:lower:]]/]
	end
 
	def is_question?(string)
		string =~ /[?]\z/
	end

	def is_empty?(string)
		string =~ /^$/
	end
end
