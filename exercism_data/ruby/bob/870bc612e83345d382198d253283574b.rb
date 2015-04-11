class Bob
  def hey(message)
    @message = message
    if empty?
      "Fine. Be that way!"
    elsif upcase?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private
    def empty?
      @message.strip.empty?
    end

    def question?
      @message.end_with?("?")
    end

    def upcase?
      @message.upcase == @message
    end
end
