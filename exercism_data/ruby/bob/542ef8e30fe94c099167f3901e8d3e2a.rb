class Bob
  def hey(message)
    if empty_message?(message)
      empty_response
    elsif question_message?(message)
      question_response
    elsif yelling_message?(message)
      yelling_response
    else
      no_match_response
    end
  end

  def empty_message?(message)
    message == "" || message.nil? || message.gsub(/\s+/, "") == ""
  end

  def empty_response
    "Fine. Be that way!"
  end

  def question_message?(message)
    message[message.length-1] == "?" && message.upcase != message
  end

  def question_response
    "Sure."
  end

  def yelling_message?(message)
    message == message.upcase
  end

  def yelling_response
    "Woah, chill out!"
  end

  def no_match_response
    "Whatever."
  end
end
