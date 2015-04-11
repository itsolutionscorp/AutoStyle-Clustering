class Bob
	def hey(input)
		exp = /[a-zA-Z]+/
		unless input.nil?
			if input.strip == ""
				return "Fine. Be that way!"
			elsif exp.match(input).nil?
				input[-1] == '?' ? "Sure." : "Whatever." 
			elsif input.upcase == input
				return "Woah, chill out!"
			elsif input[-1] == '?'
				return "Sure."
			else
				return "Whatever." 
			end
		else 
			return "Whatever."
		end
	end
end
