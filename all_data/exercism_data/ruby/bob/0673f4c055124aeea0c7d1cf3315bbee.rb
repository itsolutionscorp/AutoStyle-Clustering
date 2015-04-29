class Bob
	def hey(str)
		if str.nil? or str.strip.empty?  #str.upcase.eql?(str)
			return "Fine. Be that way!"  #"Woah, chill out!"
		#elsif str.end_with?("?")
		#	return "Sure."
		elsif str.upcase.eql?(str) #str.strip.empty? 
		    return "Woah, chill out!"  #"Fine, be that way."
		elsif str.end_with?("?")
			return "Sure."
		else
			return "Whatever."
		end
	end
end
