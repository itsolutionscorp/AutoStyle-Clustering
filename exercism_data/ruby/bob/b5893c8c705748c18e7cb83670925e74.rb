class Bob

  def hey(something)
    if nothing_in(something)
      'Fine. Be that way!'
    elsif shouting(something)
      'Woah, chill out!'
    elsif asking(something) 
      'Sure.'
    else
      'Whatever.'
    end
  end

private
  
  def nothing_in(something)
    something.to_s.strip.empty?
  end 

  def shouting(something)
    something.upcase == something
  end

  def asking(something)
    something.end_with? '?'
  end

end
