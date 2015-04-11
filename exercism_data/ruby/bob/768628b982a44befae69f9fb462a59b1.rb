class Bob
  def hey(text)
    msg = Message.new(text)

    reply_to msg
  end

  private

  def reply_to(msg)
    return chill_out if msg.shouting?
    return sure if msg.question?
    return indifferent if msg.silence?

    default
  end

  def default; 'Whatever.'; end
  def chill_out; "Woah, chill out!"; end
  def sure; "Sure."; end
  def indifferent; "Fine. Be that way!"; end
end

class Message
  def initialize(text)
    @text = text || ''
  end

  def shouting?; not silence? and @text.no_lowercase?; end
  def question?; @text.end_with? '?'; end
  def silence?;  @text.empty?; end
end

String.class_eval do
  def no_lowercase?
    self.match(/[a-z]+/).nil?
  end
end
