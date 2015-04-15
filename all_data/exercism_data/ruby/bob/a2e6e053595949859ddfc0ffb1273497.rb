class Bob
  def hey(message)
    @message = message

    case message_type
    when :blank
      'Fine. Be that way!'
    when :screaming
      "Woah, chill out!"
    when :question
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def message_type
    return :blank if is_blank?
    return :screaming if is_screaming?
    return :question if is_question?
    return :default
  end

  def is_blank?
    @message.to_s == ""
  end

  def is_screaming?
    @message == @message.upcase
  end

  def is_question?
    @message.end_with?("?")
  end
end
