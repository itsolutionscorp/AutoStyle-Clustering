# Bob the lackadaisical teenager.
class Bob
  def hey(content)
    message = Message.new(content)

    if message.is_silence?
      "Fine. Be that way!"
    elsif message.is_yelling?
      "Woah, chill out!"
    elsif message.is_question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(content)
    @content = content.to_s
  end

  def is_question?
    @content.end_with?("?")
  end

  def is_yelling?
    !is_silence? && @content.upcase == @content
  end

  def is_silence?
    @content.strip.empty?
  end
end
