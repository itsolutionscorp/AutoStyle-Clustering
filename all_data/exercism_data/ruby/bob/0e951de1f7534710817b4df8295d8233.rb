class Bob
  def hey str
    @str = str
    if shouted_at? 
      'Woah, chill out!'
    elsif questioned?
      'Sure.'
    elsif silence?
    	'Fine. Be that way!'    
    else
      'Whatever.'
    end
  end

	def shouted_at? 
		if @str == @str.upcase && @str == @str.downcase; false
		elsif @str == @str.upcase; true
		end 
	end

	def questioned?
	  @str.end_with? "?"    
	end 
	
	def silence?
		@str.empty? || @str.strip.empty?
	end

end
