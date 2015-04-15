class Bob

  def hey(statement)
    respond_to(statement ||= '')
  end

  private

  def respond_to(statement)
    return be_that_way if statement.silent?
    return chill if statement.angry?
    return sure if statement.question?
    return whatever if statement.plain?
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

class String
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
