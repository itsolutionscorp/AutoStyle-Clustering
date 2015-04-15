class Bob

	def hey(x)
		if x.match(/[a-zA-Z]/) && x == x.upcase
			"Woah, chill out!"
		elsif x[-1] == '?'
			"Sure."
		elsif x == '' || x.match(/^ +$/)
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

end
