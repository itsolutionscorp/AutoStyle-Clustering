class Bob

  def hey( phrase )

    #Thank you for the nitpicks, really appreciate the feedback!

    case 
    when is_silent?( phrase )
      'Fine. Be that way!'
    when is_shouting?( phrase )
      'Woah, chill out!'
    when is_question?( phrase )
      'Sure.'
    else
      'Whatever.'
    end

  end

  private

  def is_question?( phrase )
    phrase.end_with? '?'
  end

  def is_silent?( phrase )
    phrase.strip.empty?
  end

  def is_shouting?( phrase )
    phrase.upcase == phrase 
  end

end
