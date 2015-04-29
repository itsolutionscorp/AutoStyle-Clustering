    class Bob
    	def hey (something)
    		case 
    		when 	something.strip.empty?
    			'Fine. Be that way!'
    		when  something =~ /[A-Z]/ && something.upcase == something
    			"Woah, chill out!"
    		when something[something.size-1]=~ /\?/
    			"Sure."
    		else 
    			"Whatever."
    	end

    	

    	end
    end
