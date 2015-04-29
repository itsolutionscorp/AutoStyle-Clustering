class Bob
  def hey(message)
    Response.for Message.new(message)
  end

end

class Response

  def self.for(message)
    new.send(message.kind)
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
    KNOWN_KINDS.detect {|kind| send("#{kind}?".to_sym) } || :unknown
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
