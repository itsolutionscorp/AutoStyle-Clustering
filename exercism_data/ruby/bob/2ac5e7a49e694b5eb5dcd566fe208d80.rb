class Bob
  def hey(message)
    message = Message.new(message)
    return 'Fine. Be that way!' if message.quiet?
    return 'Woah, chill out!' if message.shout?
    return 'Sure.' if message.ask?
    'Whatever.'
  end

end

class Message

  def initialize(message)
    @message = message.to_s
  end

  def quiet?
    @message.strip.empty?
  end

  def shout?
    @message.upcase == @message
  end

  def ask?
    @message.end_with? '?'
  end

end
