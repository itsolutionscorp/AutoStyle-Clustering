class Bob
  def hey(text)
    msg = Message.new(text)

    return chill_out if msg.shouting?
    return sure if msg.question?
    return indifferent if msg.silence?

    default
  end

  private

  def default; 'Whatever.'; end
  def chill_out; "Woah, chill out!"; end
  def sure; "Sure."; end
  def indifferent; "Fine. Be that way!"; end
end

class Message
  def initialize(text)
    @text = text || ''
  end

  def shouting?; not silence? and @text.all_uppercase?; end
  def question?; @text.match /\?$/; end
  def silence?;  @text.empty?; end
end

String.class_eval do
  def all_uppercase?
    self.match(/[a-z]+/).nil?
  end
end
