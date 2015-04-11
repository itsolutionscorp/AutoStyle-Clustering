class Bob
  def hey(message_string)
    message = Message.new(message_string)
    if message.silence?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!' 
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(message_string)
    @string = message_string.to_s
  end

  def question?
    string.end_with?('?')
  end

  def shouting?
    !silence? &&
      string.upcase == string
  end

  def silence?
    string.strip.empty?
  end

  private

  attr_reader :string
end
