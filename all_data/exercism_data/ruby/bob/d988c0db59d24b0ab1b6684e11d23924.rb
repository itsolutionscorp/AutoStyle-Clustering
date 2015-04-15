class Bob
  def hey str
    @str = str
    if shouted? 
      'Woah, chill out!'
    elsif questioned?
      'Sure.'
    elsif blanked?
    	'Fine. Be that way!'    
    else
      'Whatever.'
    end
  end

	def shouted? 
	  if @str == @str.upcase && @str == @str.downcase
	  	false
		elsif @str == @str.upcase
	  	true
		else
			false 
		end 
	end

	def questioned?
	  if @str.end_with? "?"
	    true
	  else
	    false
	  end    
	end 

	def talked_forcefully_to?
	  if @str.end_with? "!"
	    true
	  else
	    false
	  end  
	end
	
	def blanked?
		@str.strip!
		if @str.empty?
			true
		else
			false
		end
	end

	end
