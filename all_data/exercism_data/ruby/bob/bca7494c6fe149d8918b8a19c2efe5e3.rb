class Bob

  def hey message
    msg = get_usable_message message

    if is_silent? msg
      "Fine. Be that way."
    elsif is_shouting? msg
      "Woah, chill out!"
    elsif is_question? msg
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def get_usable_message msg
    # Makes a copy of a valid string, but it keeps the logic simple
    msg.respond_to?(:to_str) ? msg.to_str : nil
  end

  def is_not_a_proper_msg? msg
    !msg.respond_to :to_s
  end

  def is_silent? msg
    msg.nil? || msg.empty?
  end

  def is_shouting? msg
    msg == msg.upcase
  end

  def is_question? msg
    msg.end_with? '?'
  end

end
