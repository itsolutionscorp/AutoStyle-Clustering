class Bob
	
  def hey(input)
  	if is_not_numbers_or_symbols?(input)
  		"Whatever."
  	elsif yelling?(input)
  		"Woah, chill out!"
  	elsif silence?(input)
  		"Fine. Be that way!"
  	elsif question?(input)
  		"Sure."
  	else
  		"Whatever."
  	end
  end

  private
	def is_not_numbers_or_symbols?(input)
		return true if input == input.upcase  && input == input.downcase && !question?(input) && !silence?(input)
		false
	end

  def question?(input)
    return true if input.end_with?("?")
    false
  end

  def yelling?(input)
  	return true if input == input.upcase  && input != input.downcase 
  	false
  end

  def silence?(input)
  	return true if input.strip.empty?
  	false
  end

end
