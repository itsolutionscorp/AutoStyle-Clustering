class Bob

  NOTHING_RESPONSE = "Fine. Be that way!"
  QUESTION_RESPONSE = "Sure."
  YELL_RESPONSE = "Woah, chill out!"
  DEFAULT_RESPONSE = "Whatever."

  def hey(message)
    if nothing?(message)
      bobs_response = NOTHING_RESPONSE
    elsif yelling?(message)
      bobs_response = YELL_RESPONSE
    elsif question?(message)
      bobs_response = QUESTION_RESPONSE
    else
      bobs_response = DEFAULT_RESPONSE
    end
  end

  private
    def yelling?(message)
      message == message.upcase
    end

    def question?(message)
      message.end_with?("?")
    end

    def nothing?(message)
      message.strip.length == 0
    end
end
