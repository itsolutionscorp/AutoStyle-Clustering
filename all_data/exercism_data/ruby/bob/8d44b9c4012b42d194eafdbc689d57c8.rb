class String

  def is_blank?
    self.strip.empty?
  end

  def is_upcased?
    self == self.upcase
  end

  def is_question?
    self.end_with?('?')
  end

end

class Bob
  def hey(phrase)
    if phrase.is_blank?
      'Fine. Be that way!'
    elsif phrase.is_upcased?
      'Woah, chill out!'
    elsif phrase.is_question?
        'Sure.'
    else
      'Whatever.'
    end
  end
end
