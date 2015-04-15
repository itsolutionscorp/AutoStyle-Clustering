class Bob

  def hey(arg)
    if question?(arg) and not shouting?(arg)
      "Sure."
    elsif shouting?(arg)
      "Woah, chill out!"
    elsif arg.delete(' ').empty? # and it was reading so nice
      "Fine. Be that way!"
    else
      "Whatever."
    end
    
  end

  def clean(arg)
    arg.gsub(/([^a-z0-9\s]|\s+ )/i, '')
  end

  def question?(arg)
    arg[-1] == '?'
  end

  def shouting?(arg)
    # Check if arg was already caps. or sneaking by as a number!
    clean(arg).upcase == clean(arg) and arg.downcase != arg.upcase
    
    #if clean(arg).upcase == clean(arg) and arg.downcase != arg.upcase
    #  true
    #else
    #  false
    #end
  end

end
