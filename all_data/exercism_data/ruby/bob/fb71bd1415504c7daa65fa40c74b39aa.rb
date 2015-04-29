class Bob
  def hey(message)
    return "Fine. Be that way!" if empty?(message)
    return "Woah, chill out!" if upcase?(message)
    return "Sure." if question?(message)

    "Whatever."
  end

  private
    def empty?(message)
      message.strip.empty?
    end

    def question?(message)
      message =~ /\?\z/
    end

    def upcase?(message)
      message.upcase == message
    end
end
