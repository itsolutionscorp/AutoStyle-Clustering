RESPONSES ={
    :fine => "Fine. Be that way!",
    :whoah => "Woah, chill out!",
    :sure => "Sure.",
    :whatever => "Whatever."
}

class Bob

    def hey(words)
    	statement = Statement.new(words)
        return RESPONSES[:fine] if statement.silent? 
        return RESPONSES[:whoah] if statement.shouting?  
        return RESPONSES[:sure] if statement.asking? 
        RESPONSES[:whatever]
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
