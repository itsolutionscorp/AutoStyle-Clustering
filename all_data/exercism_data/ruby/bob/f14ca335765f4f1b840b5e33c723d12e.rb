class Bob
	def hey(s)
		if !s || s == ''
			"Fine. Be that way."
		elsif s.split('').last == '?'
			"Sure."
		elsif s == s.upcase
			"Woah, chill out!"
		else
			"Whatever."
		end
	end
end
