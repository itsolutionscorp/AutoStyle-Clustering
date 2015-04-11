class Bob

	def hey(something)
		if silence?(something)
			reply = "Fine. Be that way!"
		elsif shouting?(something) 
			reply = "Woah, chill out!"
		elsif question?(something)
			reply = "Sure."
		else
			reply = "Whatever."
		end
		return reply
	end

	private

	def shouting?(something)
		uppercase_thing = something.upcase
		return something == uppercase_thing
	end 

	def question?(something)
		return something.end_with?('?')
	end

	def silence?(something)
		return something.nil? || something.strip.length == 0
	end

end
