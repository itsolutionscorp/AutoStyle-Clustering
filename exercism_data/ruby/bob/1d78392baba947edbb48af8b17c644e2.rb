class Bob
	def	hey(something)
		return  "Fine. Be that way!" if something.strip.empty?
		return  "Woah, chill out!" if  something.upcase == something  and has_letters?(something)
		return  "Sure." if something[-1] == '?'
		return 	"Whatever." 
	end

	def	has_letters?(message)
		!(message =~ /([a-z]|[A-Z])/).nil?
	end

end
