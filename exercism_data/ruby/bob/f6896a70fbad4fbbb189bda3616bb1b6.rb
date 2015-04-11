class Bob
  
	def hey(greet_text)
	    check_the_message(greet_text)
	end
	
	def feedback(greet_text)
	   return "Bob hears #{text.inspect}, and.."
	end
	
	private
	
    def check_the_message(greet_text)
      if shouting?(greet_text)
	      "Whoa, chill out!"
	    elsif is_question?(greet_text)
	      if greet_text =~ /^\d/ 
	    	  "Sure."
	    	elsif is_forceful_question?(greet_text)
    	    "Sure."
	    	end
	    elsif is_silent?(greet_text)
	    	"Fine. Be that way!"
	    else
	    	return "Whatever."
	    end
	  end
	  
    def shouting?(greet_text)
	    greet_text.upcase == greet_text && greet_text =~ /[A-Z]/
	  end
	
	  def is_silent?(greet_text)
	    greet_text.strip.empty?
	  end
	
  	def is_question?(greet_text)
	    greet_text.end_with?('?')
	  end
	  def is_forceful_question?(greet_text)
	    greet_text.upcase != greet_text && greet_text =~ /^\d?/
	  end
end
