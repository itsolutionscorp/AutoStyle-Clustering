module Response
	extend self
	def fine
    	"Fine. Be that way!"
    end

    def whoah
    	"Woah, chill out!"
    end

    def sure
    	"Sure."
    end

    def whatever
    	"Whatever." 
    end
end

class Bob
	include Response

    def hey(words)
    	statement = Statement.new(words)
        return Response.fine if statement.silent? 
        return Response.whoah if statement.shouting?  
        return Response.sure if statement.asking? 
        Response.whatever
    end

end

class Statement
	def initialize(words)
		@words = words
	end

	def silent?
		@words.strip.empty? 
	end

	def shouting?
		@words == @words.upcase && @words =~ /[A-Z]/ 
	end

	def asking?
		@words =~ /\?\z/
	end
end
