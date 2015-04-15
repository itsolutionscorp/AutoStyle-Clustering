class String

  def silent?
    self.empty? || self.match(/\A\s+\Z/)
  end

  def question?
    self.end_with?('?')
  end

  def yelled?
    self.gsub(/[\d\s,?]/,'').size > 0 && self.upcase == self
  end

end

class Bob
  def hey(message)
    response = 'Whatever.'
    response = 'Fine. Be that way!' if message.silent?
    response = 'Sure.' if message.question?
    response = 'Woah, chill out!' if message.yelled?
    response
  end

end
