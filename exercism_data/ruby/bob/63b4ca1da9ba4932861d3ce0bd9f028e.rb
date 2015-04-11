class Bob
	def hey(convo="")
		if convo == "" || convo == nil
			p "Fine. Be that way."
		elsif convo == convo.upcase
			p "Woah, chill out!"
		elsif convo[-1] == "?"
			p "Sure."
		else
			p "Whatever."
		end
	end
end
