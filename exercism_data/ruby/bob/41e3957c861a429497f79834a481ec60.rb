class String
  def yelling?
   self.upcase == self
  end

  def question?
    self.end_with? "?"
  end

  def saying_nothing?
    self =~ /^\s*$/
  end
end

class Bob
  def hey speech
    if speech.saying_nothing?
      'Fine. Be that way!'
    elsif speech.yelling?
      'Woah, chill out!'
    elsif speech.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
