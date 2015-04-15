class Bob
	def hey(talk)
		if  not_said_anything(talk)
			"Fine. Be that way!"
		elsif was_yelled_at_him(talk)
			"Woah, chill out!"
		elsif was_asked_a_question(talk)
			"Sure."
		else
			"Whatever."
		end
	end

	private
	def not_said_anything(talk)
		talk.nil? || talk.strip.empty?
	end

	def was_asked_a_question(talk)
		talk.end_with? "?"
	end

	def was_yelled_at_him(talk)
		talk.upcase.eql? talk
	end
end
