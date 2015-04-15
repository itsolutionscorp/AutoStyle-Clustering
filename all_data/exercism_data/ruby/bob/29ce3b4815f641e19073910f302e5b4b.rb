class Bob

  def hey message
    msg = get_usable_message message

    if silent? msg
      "Fine. Be that way."
    elsif shouting? msg
      "Woah, chill out!"
    elsif question? msg
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def get_usable_message msg
    msg.respond_to?(:to_str) ? msg.to_str : ''
  end

  def silent? msg
    msg.empty?
  end

  def shouting? msg
    msg == msg.upcase
  end

  def question? msg
    msg.end_with? '?'
  end

end
