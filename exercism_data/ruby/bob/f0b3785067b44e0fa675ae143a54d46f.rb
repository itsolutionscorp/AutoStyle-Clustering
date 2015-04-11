class Bob
  def hey(message)
    return "Fine. Be that way." if blank?(message)
    return "Woah, chill out!"   if upcase?(message)
    return "Sure."              if question?(message)
    "Whatever."
  end

  private

    def blank?(message)
      message == "" || message.nil?
    end

    def upcase?(message)
      message.upcase == message
    end

    def question?(message)
      message[-1] == "?"
    end

end
