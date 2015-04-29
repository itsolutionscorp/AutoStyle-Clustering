class Message
  def initialize(message)
    @content = message
  end

  def silence?
    @content.empty?
  end

  def question?
    @content.end_with?("?")
  end

  def shouting?
    !@content.match(/[A-Z]/).nil? && @content.eql?(@content.upcase)
  end
end

class Bob
  def hey(comment)
    message = Message.new(comment.strip)

    if message.silence?
      "Fine. Be that way!"
    elsif message.shouting?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
