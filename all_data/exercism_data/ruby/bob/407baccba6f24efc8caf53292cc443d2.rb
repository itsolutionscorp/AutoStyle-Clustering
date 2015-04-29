class Bob
  def hey(statement)
    statement = Statement.new(statement)

    case
      when statement.silent?
        'Fine. Be that way.'
      when statement.shouting? 
        "Woah, chill out!"
      when statement.question? 
        "Sure."
      else
        "Whatever."
    end
  end
end

class Statement < String
  def self.new(str)
    str = '' if str.nil?
    super
  end

  def silent?
    self == ''
  end

  def shouting?
    self == self.upcase
  end

  def question?
    self.end_with? '?'
  end
end
