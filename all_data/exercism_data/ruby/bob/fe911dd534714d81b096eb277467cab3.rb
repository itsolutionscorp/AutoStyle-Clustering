class Bob
	def hey(phrase)
		p = Phrase.new(phrase).strip
		case
		when p.silence?
			"Fine. Be that way!"
		when p.shouting?
	    	"Woah, chill out!"
	    when p.question?
	    	"Sure."
	    else
			"Whatever."
		end
	end
end

class Phrase < String
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
