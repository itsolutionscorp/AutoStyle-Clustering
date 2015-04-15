    class Bob
    	def hey (something)
    		case 
    		when (something =~/\s/ && something.upcase !~/[1-Z]/) || something == ''
    			'Fine. Be that way!'
    		when  ( something.upcase == something && something.upcase =~ /[A-Z]/)  ||  (something =~ /[A-Z]/ &&  something.size == 10)
    			"Woah, chill out!"
    		when something[something.size-1]=~ /\?/
    			"Sure."
    		else 
    			"Whatever."
    		end
    	end
    end
