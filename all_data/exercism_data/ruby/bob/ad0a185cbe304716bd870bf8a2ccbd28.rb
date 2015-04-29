class String
  def blank?
    self !~ /[^[:space:]]/
  end

  def yelling?
    self == self.upcase && self =~ /[A-Za-z]/
  end
end

class Bob
  def hey message
    case 
    when message.blank?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
