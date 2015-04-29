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

    def hey(statement)
    	person = Person.new(statement)
        return Response.fine if person.silent? 
        return Response.whoah if person.shouting?  
        return Response.sure if person.asking? 
        Response.whatever
    end

end

class Person
	def initialize(statement)
		@statement = statement
	end

	def silent?
		@statement.strip.empty? 
	end

	def shouting?
		@statement == @statement.upcase && @statement =~ /[A-Z]/ 
	end

	def asking?
		@statement =~ /\?\z/
	end
end
