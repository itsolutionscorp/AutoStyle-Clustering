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

class Bob
  def hey(message)
    if message.nil? or message.blank? then
      'Fine. Be that way!'
    elsif message.shouting? then
      'Woah, chill out!'
    elsif message.question? then
      'Sure.'
    else
      'Whatever.'
    end
  end
end
