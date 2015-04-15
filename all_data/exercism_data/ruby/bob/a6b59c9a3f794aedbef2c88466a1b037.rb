class Bob
  def hey(something)
    message = Message.new(something)

    return 'Fine. Be that way!' if message.silent?
    return 'Woah, chill out!' if message.shouting?
    return 'Sure.' if message.question?

    'Whatever.'
  end
end

class Message
  def initialize(text)
    @text = text
  end

  def silent?
    @text.to_s.strip.empty?
  end

  def shouting?
    @text == @text.upcase
  end

  def question?
    @text.end_with? '?'
  end
end
