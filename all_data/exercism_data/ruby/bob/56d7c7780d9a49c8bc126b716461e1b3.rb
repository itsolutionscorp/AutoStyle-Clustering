#!/usr/bin/env ruby
#Bob exercism assignment

class Bob
	def yelling?(sentence)
		if sentence == sentence.upcase and not unspoken?(sentence)
			return true
		else
			return false
		end
	end

	def asking?(sentence)
		if sentence.end_with?("?")
			return true
		else
			return false
		end
	end

	def unspoken?(sentence)
		if sentence.empty?
			return true
		else
			return false
		end
	end

	def hey(sentence)
		if yelling?(sentence)
			return "Woah, chill out!"
		elsif asking?(sentence)
			return "Sure."
		elsif unspoken?(sentence)
			return "Fine. Be that way!"
		else
			return "Whatever."
		end
	end
end
