class Bob
  def hey(text)
    return 'Fine. Be that way!' if text.blank?

    if text.yelling?
      'Woah, chill out!'
    elsif text.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class String
  def blank?
    self.empty?
  end

  def yelling?
    upcase == self
  end

  def question?
    chars.last == '?'
  end
end

class NilClass
  def blank?
    true
  end
end
