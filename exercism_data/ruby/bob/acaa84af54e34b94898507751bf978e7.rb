class Bob

  def hey message
    if silent? message
      "Fine. Be that way."
    elsif spazzing? message
      "Woah, chill out!"
    elsif question? message
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def spazzing? message
     message == message.upcase
  end

  def question? message
    message.end_with? "?"
  end

  def silent? message
    message.empty?
  end

end