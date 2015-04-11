class Bob
  def hey message
    return chill_out if shouting? message
    return sure if asking? message
    return fine if silence? message
    whatever
  end

  private

  def chill_out
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def fine
    'Fine. Be that way!'
  end

  def whatever
    'Whatever.'
  end

  def asking? message
    message.match /\?\z/
  end

  def shouting? message
    message.upcase == message && message.match(/[A-z]/)
  end

  def silence? message
    message.match /\A\s*\z/
  end
end
