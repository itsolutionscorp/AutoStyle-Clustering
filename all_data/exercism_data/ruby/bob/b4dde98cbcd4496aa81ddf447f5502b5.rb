class Bob
  def hey(statement)
    case 
    when statement.silent?   then 'Fine. Be that way!'
    when statement.shouting? then 'Woah, chill out!'
    when statement.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class String
  def silent?
    self.strip.empty?
  end

  def shouting?
    has_letters = /[a-zA-Z]/
    has_letters.match(self) && self.upcase == self
  end

  def question?
    self.end_with?('?')
  end
end
