class Bob
	def hey(phrase)
		phrase = phrase.strip
		case
		when phrase.empty?
			"Fine. Be that way!"
		when phrase.is_caps?
	    	"Woah, chill out!"
	    when phrase.is_question?
	    	"Sure."
	    else
			"Whatever."
		end
	end
end

class String
	def is_question?
		self.end_with?('?')
	end

	def is_caps?
		self.eql?(self.upcase)
	end
end
