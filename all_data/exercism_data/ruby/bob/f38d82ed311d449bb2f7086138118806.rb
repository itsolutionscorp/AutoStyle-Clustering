class Bob
  def hey(content)
    message = Message.new(content)
    return 'Fine. Be that way!' if message.silent?
    return 'Woah, chill out!' if message.shouted?
    return 'Sure.' if message.question?
    'Whatever.'
  end
end

class Message < String

  def question?
    self.end_with?("?")
  end

  def shouted?
    !self.empty? && self.upcase == self
  end

  def silent?
    self.strip.empty?
  end

end
