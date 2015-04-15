class Bob
	def hey(envia)
		if envia.lstrip.empty?
			"Fine. Be that way!"
		elseif envia[-1]=="?"
			"Woah, chill out!"
		elseif envia.upcase==envia && envia.length>2 && envia[-1]!='3'
		
			"Sure."
		else
			"Whatever."
		end
	end
end
