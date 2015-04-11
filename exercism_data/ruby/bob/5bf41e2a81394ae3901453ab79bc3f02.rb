class Bob

  def hey(input)
    
    if input.gsub(" ", "") == ("")
      response_to_silence
    elsif input == input.upcase
      response_to_yelling
    elsif input.end_with? ("?")
      response_to_question
    else
      'Whatever.'
    end
  end

  def response_to_silence
    "Fine. Be that way!"
  end

  def response_to_yelling
    'Woah, chill out!'
  end

  def response_to_question
    'Sure.'
  end


end
