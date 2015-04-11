class String
  def shouting?
    self == self.upcase && self != self.downcase
  end
  def asking?
    self[-1] == '?'
  end
  def silent?
    self.strip.empty?
  end
end

class Bob
  def hey phrase
    if phrase.shouting?
      'Woah, chill out!'
    elsif phrase.asking?
      'Sure.'
    elsif phrase.silent?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
