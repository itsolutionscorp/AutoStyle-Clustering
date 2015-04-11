class Bob

	def question?(msg)
		msg.end_with? '?'
	end

	def yell?(msg)
		msg.upcase == msg
	end

	def hey(msg)
		msg.strip!
		if msg.empty?
			response = "Fine. Be that way!"
		elsif yell? msg
			response = "Woah, chill out!"
		elsif question? msg
			response = "Sure."
		end
		response || "Whatever."
	end
end
