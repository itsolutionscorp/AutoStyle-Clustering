class Bob
	def initialize
	end

	def hey(string)
		if string.match(/[A-Z]{4}/) || string.match(/GO\!/)
			"Whoa, chill out!"
		elsif string.gsub(/\s+/, "").match(/\?$/)
			"Sure."
		elsif string.strip.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end	
	end
end
