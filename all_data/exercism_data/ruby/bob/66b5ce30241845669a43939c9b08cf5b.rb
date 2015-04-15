class Bob
  def hey(msg)
    msg = Message.new(msg)

    return 'Fine. Be that way.' if msg.silent?
    return 'Woah, chill out!'   if msg.shouting?
    return 'Sure.'              if msg.asking?
    'Whatever.'
  end
end

class Message
  def initialize(content)
    @content = content.to_s
  end

  def silent?
    @content.empty?
  end

  def shouting?
    @content.upcase == @content
  end

  def asking?
    @content.end_with? '?'
  end
end
