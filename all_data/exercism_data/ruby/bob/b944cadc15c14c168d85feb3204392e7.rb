class String

  def blank?
    self.strip.empty?
  end

  def yell?
    self == self.upcase
  end

  def question?
    self.end_with?('?')
  end

end

class Bob
  def hey(phrase)
    if phrase.blank?
      'Fine. Be that way!'
    elsif phrase.yell?
      'Woah, chill out!'
    elsif phrase.question?
        'Sure.'
    else
      'Whatever.'
    end
  end
end
