class Bob
  def hey(content)
    message = Message.new(content)
    return 'Fine. Be that way!' if message.is_not_saying_anything?
    return 'Woah, chill out!' if message.is_yelling?
    return 'Sure.' if message.is_a_question?
    'Whatever.'
  end
end

class Message < String

  def is_a_question?
    self.end_with?("?")
  end

  def is_yelling?
    !self.empty? && self.upcase == self
  end

  def is_not_saying_anything?
    self.strip.empty?
  end

end
