class String

  def upcase?
    self.upcase == self
  end

  def question?
    self[-1] == "?"
  end

  def blank?
    self.strip == ""
  end

end

class Bob

  def hey(sentence)
    if sentence.blank?
      'Fine. Be that way!'
    elsif sentence.upcase?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end
