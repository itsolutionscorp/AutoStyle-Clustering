class Bob
  def hey(message)
    if hears_silence?(message) then silence_response
    elsif hears_yelling?(message) then yelling_response
    elsif hears_question?(message) then question_response
    else default_response end
  end

  private

  def hears_yelling?(message)
    message == message.upcase
  end

  def hears_question?(message)
    message.end_with?("?")
  end

  def hears_silence?(message)
    message.nil? || message.empty?
  end

  def default_response
    "Whatever."
  end
      
  def yelling_response
    "Woah, chill out!"
  end

  def question_response
    "Sure."
  end

  def silence_response
    "Fine. Be that way."
  end
end
