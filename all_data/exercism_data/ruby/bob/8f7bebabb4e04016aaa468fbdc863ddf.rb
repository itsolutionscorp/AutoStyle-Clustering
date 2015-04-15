class String
  def all_caps?
    self == self.upcase
  end

  def question?
    self[self.size - 1] == '?'
  end
end

class Bob
  def hey(statement)
    case
    when statement.nil? || statement.empty?
      'Fine. Be that way.'
    when statement.all_caps?
      'Woah, chill out!'
    when statement.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
