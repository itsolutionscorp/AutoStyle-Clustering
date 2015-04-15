class Bob
	attr_reader :say
	def hey(say)
		@say = say
		case 
		when shouting?
			"Woah, chill out!"
		when asking?
			"Sure."
		when quiet?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

	private
	
	def shouting?
		say == say.upcase && say.match(/[A-Za-z]/)
	end
	def asking?
		say.end_with?("?")
	end
	def quiet?
		say.strip.empty?
	end
end
