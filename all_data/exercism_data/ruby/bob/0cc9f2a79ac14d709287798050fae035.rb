class Bob

	def hey(greet_text)
	    check_the_message(greet_text)
	end
	
	def feedback(greet_text)
	   return "Bob hears #{text.inspect}, and.."
	end
	
	private
	
	  def check_the_message(greet_text)
	    if greet_text.upcase == greet_text &&  greet_text =~ /[A-Z]/
	    	return "Whoa, chill out!"
	    elsif greet_text.end_with?('?')
	      if greet_text =~ /^\d/ 
	    	  return "Sure."
	    	elsif greet_text.upcase != greet_text && greet_text =~ /^\d?/
	    	  return "Sure."
	    	else
	    	  return "Whoa, chill out!"
	    	end
	    elsif greet_text.strip.empty?
	    	return "Fine. Be that way!"
	    else
	    	return "Whatever."
	    end
	  end
end
