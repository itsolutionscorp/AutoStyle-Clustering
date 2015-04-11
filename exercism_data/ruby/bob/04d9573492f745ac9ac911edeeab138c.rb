class Bob
	@brain
	def initialize
		@brain = TeenagerBrain.new
	end
    def hey(message)
    	@brain.answer(message)
    end
end

class TeenagerBrain
	def answer(message)		
		message.strip!
        if yell?(message)
    		"Woah, chill out!"
    	elsif not_interesting?(message)
    		"Fine. Be that way!"
    	elsif question?(message) 
			"Sure."
		else
			"Whatever."
		end
	end	

	private
		def yell?(message)
	        #!string[/[[:lower:]]/]
	        (!message[/[[:lower:]]/] && message =~ /[A-Z]/)
	    end

	    def question?(message)
	        message =~ /[?]\z/
	    end

	    def not_interesting?(message)
	        message =~ /^$/
	    end
end
