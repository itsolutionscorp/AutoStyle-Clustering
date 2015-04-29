class Bob

    def hey(words)
    	person = Person.new(words)
        return "Fine. Be that way!" if person.silent? 
        return "Woah, chill out!" if person.shouting?  
        return "Sure." if person.asking? 
        
        "Whatever." 
    end

end

class Person
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
