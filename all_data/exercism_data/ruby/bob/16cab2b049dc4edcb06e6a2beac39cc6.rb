# Trying to remove the instance variable per nit request
# Not crazy about this solution :)

class Bob

  def hey(input)
    determine_statement_type(input)
  end

  def determine_statement_type(input)
    if input.empty?
      'Fine. Be that way.'
    elsif input.question?
      'Sure.'
    elsif input.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class String
  def shouting?
    self == self.upcase
  end

  def question?
    self.end_with?('?')
  end
end
