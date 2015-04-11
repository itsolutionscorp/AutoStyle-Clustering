# Bob the lackadaisical teenager.
class Bob
  def hey(content)
    message = Message.new(content)

    if message.not_a_question?
      "Fine. Be that way!"
    elsif message.is_yelling?
      "Woah, chill out!"
    elsif message.is_a_question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Message
  attr_accessor :content

  def initialize(content)
    self.content = content.to_s
  end

  def is_a_question?
    content.length > 0 && content.end_with?("?")
  end

  def is_yelling?
    content.length > 0 && content.upcase == content
  end

  def not_a_question?
    content.strip.empty?
  end
end
