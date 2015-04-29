class Bob

  attr_accessor :sentence

  def hey(value)
    self.sentence = value.to_s.strip
    get_response
  end

  def get_response
    if addressing?
      'Fine. Be that way!'
    elsif yelling?
      'Woah, chill out!'
    elsif asking?
      'Sure.'
    else
      'Whatever.'
    end
  end


  def addressing?
    return true if sentence.length == 0
  end

  def yelling?
    return true if sentence == sentence.upcase
  end

  def asking?
    return true if sentence.end_with? "?"
  end

end
