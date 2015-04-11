class Bob
  def hey(message)
    message = Phrase.new(message.to_s)
    if    message.silence?  then "Fine. Be that way."
    elsif message.shouting? then "Woah, chill out!"
    elsif message.question? then "Sure."
    else "Whatever." end
  end
end

class Phrase < String
  alias :silence? :empty?

  def shouting?
    self == self.upcase
  end

  def question?
    self.end_with? '?'
  end
end
