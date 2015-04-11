class Bob
  def hey(message)
    send(Message.new(message).kind || :unknown)
  end

  def quiet
    'Fine. Be that way!'
  end

  def shout
    'Woah, chill out!'
  end

  def ask
    'Sure.'
  end

  def unknown
    'Whatever.'
  end

end

class Message

  KNOWN_KINDS = [:quiet, :shout, :ask]

  def initialize(message)
    @message = message.to_s
  end

  def kind
    KNOWN_KINDS.select {|kind| send("#{kind}?".to_sym) }
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
