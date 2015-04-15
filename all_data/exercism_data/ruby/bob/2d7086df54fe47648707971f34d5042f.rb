class Bob
  def hey(message)
    Response.to(message)
  end
end

class Response
  attr_reader :message

  def self.to(message)
    new(message).to_s
  end

  def initialize(message)
    @message = message
  end

  def to_s
    if shout?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    elsif silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private
  def shout?
    message.upcase == message && message =~ /[a-zA-Z]/
  end

  def question?
    '?' == message[-1,1]
  end

  def silence?
    '' == message.strip
  end
end
