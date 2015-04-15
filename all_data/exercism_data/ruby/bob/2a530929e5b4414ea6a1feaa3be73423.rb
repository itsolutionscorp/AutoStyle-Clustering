class Bob
  def hey str    
    if silence?(str)
 	  	'Fine. Be that way!'
    elsif shouted_at?(str)
      'Woah, chill out!'
    elsif questioned?(str)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def shouted_at?(str)
    str == str.upcase && str == str.downcase ? false : str == str.upcase
  end

  def questioned?(str)
		str.end_with? "?"
  end

  def silence?(str)
    str.strip.empty?
  end

end
