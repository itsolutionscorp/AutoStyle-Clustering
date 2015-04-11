class Bob

  def hey(something)
    if silence?(something)
      reply = "Fine. Be that way!"
    elsif shouting?(something) 
      reply = "Woah, chill out!"
    elsif question?(something)
      reply = "Sure."
    else
      reply = "Whatever."
    end
    reply
  end

  private

  def shouting?(something)
    something == something.upcase
  end 

  def question?(something)
    something.end_with?('?')
  end

  def silence?(something)
    something.to_s.strip.empty?
  end

end
