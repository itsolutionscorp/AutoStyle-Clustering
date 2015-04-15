class Bob
  def hey(m)
    message = Message.new(m)

    if message.silent?
      return 'Fine. Be that way.'
    elsif message.loud?
      return 'Woah, chill out!'
    elsif message.question?
      return 'Sure.'
    end

    return 'Whatever.'
  end
end

class Message
  def initialize(content)
    @content = content.to_s
  end

  def silent?
    return @content == ''
  end

  def loud?
    return @content.index(/[a-z]/).nil?
  end

  def question?
    return !@content.index(/\?$/).nil?
  end
end
