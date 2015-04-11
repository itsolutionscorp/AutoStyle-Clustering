class Bob

  def hey message
    message = Message::new(message)
    give_response(message)
  end

  private

  def give_response(message)
    if message.blank?
      "Fine. Be that way!"
    elsif message.yelling?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message

  attr_reader :content
  def initialize content
    @content = content
  end

  def blank?
    @content.strip.empty?
  end

  def yelling?
    @content == @content.upcase
  end

  def question?
    @content.end_with?("?")
  end
end
