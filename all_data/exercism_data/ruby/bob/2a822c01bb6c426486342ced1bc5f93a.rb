class Bob

  def hey(message)
    case
    when normal_response(message)
     "Fine. Be that way!" 
    when yelling_response(message)
      "Woah, chill out!"
    when question_response(message)
      "Sure."
    else 
      "Whatever."
    end
  end

  private

  def normal_response(message)
    message.to_s.strip.empty?
  end

  def yelling_response(message)
    !!(/^[^a-z]*$/ =~ message)
  end

  def question_response(message)
    message[-1] == '?' 
  end

end
