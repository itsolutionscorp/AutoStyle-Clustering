class Bob
  def initialize
  end

  def hey(message)
    if message.all_capital?
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    elsif message.has_no_letters?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class String
  def all_capital?
    (self.upcase == self) && (self.scan(/[a-zA-Z]/).length != 0)
  end

  def has_no_letters?
    self.empty? || self.blank?
  end

  def blank?
    self.scan(/[a-zA-Z0-9]/).empty?
  end
end
