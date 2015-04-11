class Bob
  def hey message
    greet = greet_for_yell message
    greet ||= greet_for_question message
    greet ||= greet_for_empty message
    greet ||=  default_greet
  end

  def greet_for_yell message
    if message.upcase.match(/[A-Z]/) && message == message.upcase
      "Woah, chill out!"
    end
  end
  def greet_for_question message
    if message.end_with?("?")
      "Sure." 
    end
  end

  def greet_for_empty message
    if message.strip == ""
     'Fine. Be that way!' 
    end
  end

  def default_greet
    "Whatever."
  end
end
