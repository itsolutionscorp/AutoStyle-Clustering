class Bob
  def hey(message)
    the_message = Message.new(message)
    return 'Fine. Be that way!' if the_message.quiet?
    return 'Woah, chill out!' if the_message.shout?
    return 'Sure.' if the_message.ask?
    'Whatever.'
  end

end

class Message

  def initialize(message)
    @message = message
  end

  def quiet?
    @message.to_s.strip.empty?
  end

  def shout?
    @message.upcase == @message
  end

  def ask?
    @message.end_with? '?'
  end

end
