class Bob

  def hey(input)

    message = Message.new(input)

    if message.silent?
      'Fine. Be that way.'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.asking?
      'Sure.'
    else
      'Whatever.'
    end

  end

end

class Message

  def initialize(text)
    @text = text || ''
  end

  def asking?
    @text.end_with?("?")
  end

  def capitalized?
    @text == @text.upcase
  end

  def silent?
    @text.empty?
  end

  def shouting?
    !silent? && capitalized?
  end

end
