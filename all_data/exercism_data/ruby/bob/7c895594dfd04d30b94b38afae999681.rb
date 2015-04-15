class Bob
  def hey(message)
    message = Message.new message

    case
      when message.question? then 'Sure.'
      when message.shout? then 'Woah, chill out!'
      when message.nothing? then 'Fine. Be that way.'
      else 'Whatever.'
    end	
  end
end

class Message

  attr_reader :message

  def initialize(message)
      @message = message.to_s
  end

  def question?
    message.end_with? "?"
  end

  def shout?
    message.upcase == message && !message.empty?
  end

  def nothing?
    message.empty?
  end
end
