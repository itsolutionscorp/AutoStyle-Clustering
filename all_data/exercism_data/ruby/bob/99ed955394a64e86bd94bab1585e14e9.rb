class String
  def yelling?
    self == self.upcase
  end

  def question?
    self[-1] == '?'
  end
end

class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.strip.empty?
    return 'Woah, chill out!' if msg.yelling?
    return 'Sure.' if msg.question?

    'Whatever.'
  end
end
