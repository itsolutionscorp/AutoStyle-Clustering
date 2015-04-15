class Bob
	def hey(str)
		if str == nil || str.empty?
			return "Fine. Be that way."
		elsif str == str.upcase
			return "Woah, chill out!"
		elsif str.end_with? "?"
			return "Sure."
		else
			return "Whatever."
		end		
	end
end
