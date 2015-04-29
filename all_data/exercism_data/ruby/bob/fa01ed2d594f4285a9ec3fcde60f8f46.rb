class String
  def question?
    self[-1] == '?'
  end

  def yell?
    self.scan(/[a-zA-Z]/).any? && self.upcase == self
  end

  def silence?
    self.strip.empty?
  end
end

class Bob
  def hey sentence
    case
    when sentence.silence? then 'Fine. Be that way!'
    when sentence.yell? then 'Woah, chill out!'
    when sentence.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end
