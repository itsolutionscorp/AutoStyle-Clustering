class Bob

  def hey(speech)
    speech.strip!

    if speech.empty?
      "Fine. Be that way!"
    elsif is_shouting?(speech)
      "Woah, chill out!"   
    elsif is_a_question?(speech)
      "Sure."
    else
      "Whatever."
    end
  end

  def is_a_question?(speech)
    speech.end_with?("?")
  end
  
  def is_shouting?(speech)
    speech.upcase == speech && speech =~ /[A-z]/
  end

end
