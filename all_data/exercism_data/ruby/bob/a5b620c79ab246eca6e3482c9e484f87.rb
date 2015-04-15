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

require 'delegate'
class Message < SimpleDelegator

  def initialize message
    super(message)
  end

  def blank?
    self.strip.empty?
  end

  def yelling?
    self == self.upcase
  end

  def question?
    self.end_with?("?")
  end
end
