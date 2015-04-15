class Bob
  def hey(text)
    msg = Message.new(text)
    case
    when msg.question? then "Sure."
    when msg.yelling? then "Woah, chill out!"
    when msg.silence? then "Fine. Be that way."
    else
      "Whatever."
    end
  end
end


class Message
  attr_reader :text

  def initialize(text)
    @text = text.to_s
  end

  def silence?
    text.length == 0
  end

  def yelling?
    text.length > 0 && text == text.upcase
  end

  def question?
    text.end_with? "?"
  end
end
