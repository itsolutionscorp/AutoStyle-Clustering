class Bob
  def hey(msg)
    return 'Fine. Be that way!' if msg.slience?
    return 'Woah, chill out!' if msg.yelling?
    return 'Sure.' if msg.questioning?
    'Whatever.'
  end
end

class String
  def slience?
    self.strip.empty?
  end

  def yelling?
    self.upcase == self && self.downcase != self
  end

  def questioning?
    self =~ /\?\z/
  end
end
