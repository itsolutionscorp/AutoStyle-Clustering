class Bob

	def hey say
		if say == say.upcase && say.match(/[a-zA-Z]/)
			"Woah, chill out!"
		elsif say[-1] == '?'
			"Sure."
		elsif say.strip == ""
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

end
