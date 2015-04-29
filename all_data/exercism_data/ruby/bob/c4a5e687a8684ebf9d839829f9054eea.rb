class Bob
  attr_accessor :message

  def hey(message)
    self.message = message

    case
    when question?   then "Sure."
    when yelled?     then "Woah, chill out!"
    when no_message? then "Fine. Be that way!"
    else "Whatever."
    end
  end

  def question?
    !yelled? && message.end_with?("?")
  end

  def yelled?
    !no_message? && message.upcase == message
  end

  def no_message?
    message.strip.empty?
  end
end
