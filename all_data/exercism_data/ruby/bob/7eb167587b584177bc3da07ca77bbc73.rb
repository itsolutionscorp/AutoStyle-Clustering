class Bob

	def hey(input)

		if input.strip.empty?
			return_value = "Fine. Be that way!"
		elsif input.upcase == input
			return_value = "Woah, chill out!"
		elsif input[-1] == "?"
			return_value = "Sure."
		else
			return_value = "Whatever."
		end

	end
end
