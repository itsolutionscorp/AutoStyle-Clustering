class Bob
	def hey(phrase)
		phrase = phrase.strip
		case
		when phrase.empty?
			"Fine. Be that way!"
		when phrase.eql?(phrase.upcase)
	    	"Woah, chill out!"
	    when phrase[-1] == "?"
	    	"Sure."
	    else
			"Whatever."
		end
	end
end
