class Bob
	def hey(phrase)
		if phrase == nil || phrase == ""
			return "Fine. Be that way."
		elsif phrase.scan(/[A-Z]/).size > 2 || ( phrase.match(/\!$/) && phrase.scan(/[A-Z]/).size >= 2)
			return 	"Woah, chill out!"
		elsif phrase.match(/\?$/)
			return "Sure."
		else
			return "Whatever."
		end
	end
end
