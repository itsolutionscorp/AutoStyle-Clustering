class String
  # see no evil, hear no evil, speak no evil
  # monkey patch

  def all_caps?
    self.upcase == self
  end

  def question?
    chars.last == '?'
  end
end

class Bob
  def hey(message)
    return 'Fine. Be that way!' if String(message).empty?
    return 'Woah, chill out!' if message.all_caps?
    return 'Sure.' if message.question?
    'Whatever.'
  end
end
