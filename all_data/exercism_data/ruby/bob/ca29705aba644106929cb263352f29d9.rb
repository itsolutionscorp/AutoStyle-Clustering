class Bob

  def hey(message)
    @message = message
    if silence?
      "Fine. Be that way!"
    elsif shouting?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?
    @message.strip == ''
  end

  def shouting?
    @message == @message.upcase
  end

  def question?
    @message[-1] == "?"
  end

end
