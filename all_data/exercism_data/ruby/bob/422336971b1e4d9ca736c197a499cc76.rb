class Bob

	def hey(comment)
		case
			when nothing_said?(comment)
				"Fine. Be that way!"
			when shouting?(comment)
				"Woah, chill out!"
			when question?(comment)
				"Sure."
			else	
				"Whatever."
		end
	end


	private
		def nothing_said?(comment)
			comment.strip.empty?
		end

		def shouting?(comment)
			comment.upcase == comment
		end

		def question?(comment)
			comment.end_with? "?"
		end
end
