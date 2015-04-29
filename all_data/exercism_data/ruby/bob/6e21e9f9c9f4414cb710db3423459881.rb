class Bob
	def hey(str)
		if str.nil? or str.strip.empty? 
			return "Fine. Be that way!"
		elsif str.upcase.eql?(str) 
		    return "Woah, chill out!"
		elsif str.end_with?("?")
			return "Sure."
		else
			return "Whatever."
		end
	end
end
