class String
  def blank?
    self.strip.empty?
  end
  def shouting?
    self.eql? self.upcase
  end
  def question?
    self.end_with? '?'
  end
end

class NilClass
  def blank?
    true
  end
end

class Bob
  def hey(message)
    case
      when message.blank?    then 'Fine. Be that way!'
      when message.shouting? then 'Woah, chill out!'
      when message.question? then 'Sure.'
      else 'Whatever.'
    end
  end
end
