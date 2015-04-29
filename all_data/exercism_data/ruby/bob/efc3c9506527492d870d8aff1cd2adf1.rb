module TeenagerSaying 

  def a_question?
    self.end_with? '?'
  end

  def yelling?
    self == self.upcase
  end

  def nothing?
    self.nil? or self.empty?
  end

end

class Bob

  def hey(got_told)
    got_told.extend TeenagerSaying
    return 'Fine. Be that way.' if got_told.nothing?
    return 'Woah, chill out!' if got_told.yelling?
    return 'Sure.' if got_told.a_question?
    'Whatever.'
  end

end
