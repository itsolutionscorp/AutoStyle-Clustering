class Bob

	def hey (speach)
		if speach.nil? 			then return "Fine. Be that way!" end
		if speach.length == 0 		then return "Fine. Be that way!" end
		if speach == speach.upcase 	then return "Woah, chill out!" end
		if speach[-1] == "."		then return "Whatever." end
		if speach[-1] == "!"		then return "Whatever." end
		if speach[-1] == "?" 		then return "Sure." end
	end

end
