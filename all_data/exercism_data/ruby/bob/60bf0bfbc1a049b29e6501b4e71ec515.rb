class Bob

  def hey(statement)
    respond_to Message.new(statement.to_s)
  end

  private

  def respond_to(message)
    return be_that_way if message.silent?
    return chill if message.angry?
    return sure if message.question?
    return whatever if message.plain?
  end

  def whatever
    'Whatever.'
  end

  def chill
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def be_that_way
    'Fine. Be that way!'
  end
end

class Message < String
  def angry?
    self == self.upcase
  end

  def silent?
    self.strip.empty?
  end

  def question?
    self.end_with?('?')
  end

  def plain?
    self != self.upcase
  end
end
