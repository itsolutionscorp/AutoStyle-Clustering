class Bob
	def hey(s)
		if s.split('').last == '?'
			"Sure."
		elsif s == ''
			"Fine. Be that way!"
		elsif s == s.upcase
			"Woah, chill out!"
		else
			"Whatever."
		end
	end
end
