class Bob
  def initialize

  end

  def hey(input)
  	if input.length==0 || input.delete(' ').length ==0 
  		'Fine. Be that way!'
  	elsif input.sub(/[1234567890]/,'').length ==1 && input.sub(/[1234567890]/,'').end_with?('?')
  		'Sure.'
  	elsif input.upcase == input && input.end_with?('?')
  		'Woah, chill out!'
  	elsif input.end_with?('?')
  		'Sure.'
  	elsif input.upcase == input 
  		'Woah, chill out!'
  	
    else
      'Whatever.'
    end

  end

end

# && (input.end_with?('?') || input.end_with?('!'))
