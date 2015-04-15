class Bob
  attr_reader :message

  def hey(message)
    @message = message

    if ignored?
      return "Fine. Be that way!"
    elsif shouted_at?
      return "Woah, chill out!"
    elsif questioned?
      return "Sure."
    end

    return "Whatever."
  end

  private

    def questioned?
      message[-1] == "?"
    end

    def shouted_at?
      message == message.upcase
    end

    def ignored?
      message.strip.empty?
    end
end
