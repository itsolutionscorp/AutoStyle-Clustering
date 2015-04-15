class Bob
	attr_accessor :teenager

	def initialize
	end

	def hey(saying)
		case
		when saying.to_s == ""
			"Fine. Be that way."
		when saying == saying.upcase
			"Woah, chill out!"
		when saying[-1] == "?"
			"Sure."
		else
			"Whatever."
		end

	end
end
