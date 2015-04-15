class Bob

	def Bob
		@teenager = Bob.new
	end

	def hey(string)
		if string.empty? == true || string.include?("    ")
			return 'Fine. Be that way!'
		elsif string[-2..-1] == "1!" || string.upcase.scan(/[a-zA-Z!?]+/).join(" ") == string || string[-3..-1] == "GO!"
			return 'Woah, chill out!'
		elsif string[-1] == "?"
			return "Sure."
		else
			return "Whatever."
		end
	end
end
