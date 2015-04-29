class Bob
  def hey(message)
    if message.completely_empty? then 'Fine. Be that way!'
    elsif message.upcase? then 'Woah, chill out!'
    elsif message.question? then 'Sure.'
    else "Whatever."
    end
  end
end

class String
  def upcase?
    self == self.upcase
  end

  def question?
    self[-1] == '?'
  end

  def completely_empty?
    self.strip.empty?
  end
end
