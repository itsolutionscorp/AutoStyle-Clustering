class Bob
	def hey(input)
		input.chomp!

		if input.strip.empty? then return "Fine. Be that way!" end
		if (input.upcase == input) then return "Woah, chill out!" end
		if input.end_with?("?") then return "Sure." end
		else return "Whatever."
	end
end
