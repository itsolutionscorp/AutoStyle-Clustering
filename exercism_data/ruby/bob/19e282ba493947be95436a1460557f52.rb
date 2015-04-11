class Bob
	def hey(phrase)
		phrase = phrase.strip
		case
		when phrase.silence?
			"Fine. Be that way!"
		when phrase.shouting?
	    	"Woah, chill out!"
	    when phrase.question?
	    	"Sure."
	    else
			"Whatever."
		end
	end
end

class String
	def silence?
		self.empty?
	end
	def shouting?
		self.eql?(self.upcase) && !self.eql?(self.downcase)
	end
	def question?
		self.end_with?('?')
	end
end
