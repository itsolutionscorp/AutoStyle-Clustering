class Bob

  def hey(message)
    case type(message)
    when :blank
      "Fine. Be that way!"
    when :caps
      'Woah, chill out!'
    when :question
      'Sure.'
    else
      'Whatever.'
    end
  end

  def type(message)
    return :blank if blank(message)
    return :caps if all_caps(message)
    return :question if question(message)
  end

  def blank(message)
    true if message == nil || message.lstrip.length == 0
  end

  def all_caps(message)
    message.upcase == message
  end

  def question(message)
    message[-1] == "?"
  end
end
