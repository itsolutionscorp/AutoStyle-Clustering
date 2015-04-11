class Bob
	def hey(str)
		if str == str.upcase
			"Woah, chill out!"
		elsif str.include? "?"
			"Sure."
		elsif str.empty?
		  "Fine. Be that way!"
		else
			"Whatever."
		end
	end
end
