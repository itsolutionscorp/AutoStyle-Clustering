class Bob
  def initialize
  end

  def hey(message)
    if message.yelling?
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    elsif message.silent?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class String
  def yelling?
    (self.upcase == self) && (self.scan(/[a-zA-Z]/).length != 0)
  end

  def silent?
    self.strip == ""
  end
end
