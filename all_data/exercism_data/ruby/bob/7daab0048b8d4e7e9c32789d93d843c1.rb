class Bob
  def hey(message)
    message = Message.new(message)
    if message.angry?
      'Woah, chill out!'
    elsif message.blank?
      'Fine. Be that way!'
    elsif message.question?
      'Sure.'
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(message)
    @content = message.to_s
  end

  def angry?
    @content.upcase == @content && !blank?
  end

  def question?
    @content.end_with? "?"
  end

  def blank?
    @content.lstrip.empty?
  end
end
