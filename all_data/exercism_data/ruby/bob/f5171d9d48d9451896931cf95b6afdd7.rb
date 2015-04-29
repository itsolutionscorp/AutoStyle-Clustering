class Bob
	def hey(input)
		input.strip!
		if isYelling?(input)
			"Woah, chill out!"
		elsif isQuestion?(input)
			"Sure."
		elsif input.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

	def isYelling?(input)
		if /^[A-Z|0-9|\W]+$/ =~ input
			if /[A-Z]/ =~ input
				return true
			end
		end
		false
	end
	def isQuestion?(input)
		input[input.length - 1] == "?"
	end
end
