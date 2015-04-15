class Bob
  def hey str
    @str = str
    if shouted_at? 
      'Woah, chill out!'
    elsif questioned?
      'Sure.'
    elsif blanked?
    	'Fine. Be that way!'    
    else
      'Whatever.'
    end
  end

	def shouted_at? 
		if @str == @str.upcase && @str == @str.downcase
	  	false
		elsif @str == @str.upcase
	  	true
		end 
	end

	def questioned?
	 true if @str.end_with? "?"    
	end 
	
	def blanked?
		@str.strip!
		true if @str.empty?
	end

	end
