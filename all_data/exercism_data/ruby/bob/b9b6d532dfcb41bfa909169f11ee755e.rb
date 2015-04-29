class String
  def all_caps?
   self.upcase == self
  end

  def question?
    self.end_with? "?"
  end

  def saying_nothing?
    self.gsub(" ", '').empty?
  end
end

class Bob
  def hey speech
    if speech.saying_nothing?
      'Fine. Be that way!'
    elsif speech.all_caps?
      'Woah, chill out!'
    elsif speech.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
