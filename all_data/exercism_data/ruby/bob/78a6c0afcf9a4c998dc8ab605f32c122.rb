class Bob
	
	def hey said_to_bob
		return "Fine. Be that way!" if silence? said_to_bob
		return "Woah, chill out!" if shouting? said_to_bob
		return "Sure." if asking_question? said_to_bob
		"Whatever."
	end

	def shouting? said_to_bob
		if said_to_bob =~ /.*[a-zA-Z].*/
			return said_to_bob == said_to_bob.upcase
		end
	end

	def asking_question? said_to_bob
		said_to_bob =~ /.*\?\z/
	end

	def silence? said_to_bob
		said_to_bob =~ /\A *\z/
	end

end
