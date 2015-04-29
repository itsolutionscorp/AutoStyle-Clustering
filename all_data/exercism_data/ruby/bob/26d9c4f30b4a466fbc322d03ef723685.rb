class Bob
	def initialize
	end

	def hey message
		if blank? message
			"Fine. Be that way!"
		elsif all_caps? message 
			"Woah, chill out!"	
		elsif question? message
			"Sure."
		else
			"Whatever."
		end
	end

	private
		def blank? message
			message.gsub(/\s+/, "") == ""
		end

		def all_caps? message
			message.upcase == message
		end

		def question? message
			message.end_with?("?")
		end
end
