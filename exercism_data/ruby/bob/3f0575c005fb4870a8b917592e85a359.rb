class String
  def blank?
    self.strip.empty?
  end

  def yelling?
    self == self.upcase && self != self.downcase
  end

  def question?
    self.end_with? '?'
  end
end

class Bob
  def hey message
    case 
    when message.blank?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
