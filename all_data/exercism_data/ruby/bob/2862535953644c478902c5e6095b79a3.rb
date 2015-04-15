class Bob

	def hey(comment)
		if nothing_said?(comment);	"Fine. Be that way!"			
		elsif shouting?(comment);	"Woah, chill out!"
		elsif question?(comment);	"Sure."
		else;	"Whatever."
		end
	end


	private
		def nothing_said?(comment)
			comment.strip.empty?
		end

		def shouting?(comment)
			comment.match /^[^a-z]+$/
		end

		def question?(comment)
			comment[-1] == "?"
		end


end
