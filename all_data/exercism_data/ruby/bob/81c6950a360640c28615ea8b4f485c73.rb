class Bob
  def hey message
    msg = make_replyable message

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

  def make_replyable msg
    msg.respond_to?(:to_str) ? msg.to_str : ''
  end

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
