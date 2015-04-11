
class Bob
	def hey(to_bob)
		if shout?(to_bob) then 'Woah, chill out!'
		elsif say_nothing?(to_bob) then 'Fine. Be that way!'
		elsif ask_question?(to_bob) then 'Sure.'	
	    else 'Whatever.'
	    end
    end

    private
    
    def shout?(words)
    	words.upcase == words && words.downcase != words
    end

    def say_nothing?(words)
    	words.strip.empty?
    end

    def ask_question?(words)
    	words.end_with?("?")	
    end	

end
