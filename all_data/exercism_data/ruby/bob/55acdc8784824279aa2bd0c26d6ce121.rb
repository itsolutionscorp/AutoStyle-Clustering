class Bob

	def initialize
		@silent = "Fine. Be that way!"
      	@anger = "Woah, chill out!"
      	@whatever = "Whatever."
      	@question = "Sure."
    end

	def hey(msg)

		if msg.gsub(/\s+/, "").empty?
			@silent
		elsif msg.upcase === msg && msg.match('.*[A-Z].*[A-Z].*')
			@anger
		elsif msg.end_with?('?')
			@question 
		else
			@whatever
		end
		
	end

end
