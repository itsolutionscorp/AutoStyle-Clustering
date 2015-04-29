UPPERCASE = ('A' .. 'Z').to_a
LOWERCASE = ('a' .. 'z').to_a

class Bob
	def hey(msg)
		if (msg.split('') & LOWERCASE).empty? && !(msg.split('') & UPPERCASE).empty?
			"Woah, chill out!"
		elsif msg[-1] == "?"
			"Sure."
		elsif msg.strip.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end	
	end
end
