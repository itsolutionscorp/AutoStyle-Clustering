class Bob
  def hey(message)
    @message = message

    if silent?
      "Fine. Be that way!"
    elsif yelling?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silent?
    @message.strip == ''
  end

  def question?
    @message.end_with? "?"
  end

  def yelling?
    @message.upcase == @message
  end
end
