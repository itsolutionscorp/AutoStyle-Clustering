class Bob
  def hey(text)
    msg = Message.new(text)

    case
    when msg.question?   then "Sure."
    when msg.yelled?     then "Woah, chill out!"
    when msg.no_message? then "Fine. Be that way!"
    else "Whatever."
    end
  end
end

class Message
  def initialize(text)
    @text = text
  end

  def question?
    !yelled? && @text.end_with?("?")
  end

  def yelled?
    !no_message? && @text.upcase == @text
  end

  def no_message?
    @text.strip.empty?
  end
end
