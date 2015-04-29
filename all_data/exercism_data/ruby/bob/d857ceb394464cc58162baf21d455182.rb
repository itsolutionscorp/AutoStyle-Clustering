#!/usr/bin/env ruby
#Bob exercism assignment

class String
	def yelling?
		if self == self.upcase and not self.unspoken?
			return true
		else
			return false
		end
	end

	def asking?
		if self.end_with?("?")
			return true
		else
			return false
		end
	end

	def unspoken?
		if self.empty?
			return true
		else
			return false
		end
	end
end

class Bob
	def hey(sentence)
		if sentence.yelling?
			return "Woah, chill out!"
		elsif sentence.asking?
			return "Sure."
		elsif sentence.unspoken?
			return "Fine. Be that way!"
		else
			return "Whatever."
		end
	end
end
