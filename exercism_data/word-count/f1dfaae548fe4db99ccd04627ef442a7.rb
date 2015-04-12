class Bob
  def hey(message)
    return "Fine. Be that way." if blank?(message)
    return "Woah, chill out!"   if shouting?(message)
    return "Sure."              if question?(message)
    "Whatever."
  end

  private

    def blank?(message)
      message.to_s.strip.empty?
    end

    def shouting?(message)
      message.upcase == message
    end

    def question?(message)
      message.end_with?("?")
    end

end
