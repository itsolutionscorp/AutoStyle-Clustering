class String
  def is_silence?
    self.strip == ''
  end

  def is_yelling?
    self.upcase == self
  end

  def is_asking?
    self.end_with? '?'
  end 
end

class Bob 
  def hey(msg)
    if msg.is_silence?
      return 'Fine. Be that way!'
    end

    if msg.is_yelling?
      return 'Woah, chill out!'
    end

    if msg.is_asking?
      return 'Sure.'
    end

    'Whatever.'
  end
end
