class Bob

  def hey(string)
    if string.shouting?
      'Woah, chill out!'
    elsif string.asking?
      'Sure.'
    elsif string.silence?
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

end

class String

  def shouting?
    self.match(/[A-Z]/) && self.upcase == self
  end

  def asking?
    self[-1] == "?"
  end

  def silence?
    self.strip.empty?
  end
end
