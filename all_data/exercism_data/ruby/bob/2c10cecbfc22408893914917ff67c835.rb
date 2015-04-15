class Bob

  def hey msg
    if is_silent?(msg)
      "Fine. Be that way."
    elsif is_shouting?(msg)
      "Woah, chill out!"
    elsif is_question?(msg)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_silent? msg
    !msg || msg.empty?
  end

  def is_shouting? msg
    msg == msg.upcase
  end

  def is_question? msg
    msg.end_with? '?'
  end

end
