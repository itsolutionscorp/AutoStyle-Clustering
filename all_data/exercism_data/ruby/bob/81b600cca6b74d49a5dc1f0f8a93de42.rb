class Bob
	def hey(sth)
		#remove spaces
		sth.gsub!(/\s/, "")
		#check empty
		return "Fine. Be that way!" if sth.empty?
		#check for upcase
		return "Woah, chill out!" if (sth == sth.upcase) && (sth.index(/[A-Z]/))
		#check question
		return "Sure." if sth[-1] == "?"
		#otherwise
		return "Whatever."
	end
end
