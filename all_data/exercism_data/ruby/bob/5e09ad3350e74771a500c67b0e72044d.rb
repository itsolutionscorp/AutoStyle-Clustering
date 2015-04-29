class Bob

  ORDERED_RESPONSES = {
    quiet: 'Fine. Be that way!',
    shout: 'Woah, chill out!',
    ask: 'Sure.',
    unknown: 'Whatever.'
  }

  def hey(message)
    response_for Message.new(message)
  end

  def response_for(message)
    message_type_recognized = message_kinds_for(message).first || :unknown
    ORDERED_RESPONSES[message_type_recognized]
  end

  def message_kinds_for(message)
    ORDERED_RESPONSES.keys & message.kinds
  end

end

class Message

  KNOWN_KINDS = [:ask, :quiet, :shout]

  def initialize(message)
    @message = message.to_s
  end

  def kinds
    KNOWN_KINDS.select{|kind| send "#{kind}?" }
  end

  def ask?
    @message.end_with? '?'
  end

  def quiet?
    @message.strip.empty?
  end

  def shout?
    @message.upcase == @message
  end

end
