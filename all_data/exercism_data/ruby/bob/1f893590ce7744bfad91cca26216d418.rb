class Bob
  def hey(text)
    msg = Message.new(text)

    case
    when msg.question? then "Sure."
    when msg.yell?     then "Woah, chill out!"
    when msg.silence?  then "Fine. Be that way!"
    else "Whatever."
    end
  end
end

class Message
  def initialize(text)
    @text = text
  end

  def question?
    !yell? && @text.end_with?("?")
  end

  def yell?
    !silence? && @text.upcase == @text
  end

  def silence?
    @text.strip.empty?
  end
end
