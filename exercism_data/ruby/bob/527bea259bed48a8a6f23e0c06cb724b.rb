class Bob
  def hey(message)
    msg = message.to_s
    return fine if msg.strip.empty?
    return woah if msg == msg.upcase
    return sure if msg.end_with?('?')
    whatever
  end

  private

  def fine
    'Fine. Be that way!'
  end

  def woah
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def whatever
    "Whatever."
  end
end
