require 'test/unit'

class Bob 

	def hey(napis)

		c = napis.byteslice(-1)

		if napis.strip == ""
			return 'Fine. Be that way!'
		end

		if napis.delete("a-zA-Z") == napis
			if c == '?'
				return 'Sure.'
			end
			return 'Whatever.'
		end

		if napis == napis.upcase
			return 'Woah, chill out!'
		end

		if c == '?'
			return 'Sure.'
		end
		
		return 'Whatever.'

	end
end
