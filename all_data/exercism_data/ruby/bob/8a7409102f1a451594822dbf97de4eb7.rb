class Bob
	def hey(talk)
		if  nothing_said?(talk)
			"Fine. Be that way!"
		elsif yelled_at_him?(talk)
			"Woah, chill out!"
		elsif question_asked?(talk)
			"Sure."
		else
			"Whatever."
		end
	end

	private
	def nothing_said?(talk)
		talk.nil? || talk.strip.empty?
	end

	def question_asked?(talk)
		talk.end_with? "?"
	end

	def yelled_at_him?(talk)
		talk.upcase.eql? talk
	end
end
