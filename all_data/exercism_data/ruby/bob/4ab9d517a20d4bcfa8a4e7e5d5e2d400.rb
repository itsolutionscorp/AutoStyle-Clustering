class Bob
  def hey message
    msg = message.to_s

    if silent? msg
      "Fine. Be that way."
    elsif shouting? msg
      "Woah, chill out!"
    elsif asking_question? msg
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent? msg
    msg.empty?
  end

  def shouting? msg
    msg == msg.upcase
  end

  def asking_question? msg
    msg.end_with? '?'
  end
end
