class Bob
  def hey(expression)
    case expression
    when /\A[A-Z\d\W]+\z/
      'Woah, chill out!'
    when /\A[a-zA-Z\d\W]+\?\z/
      'Sure.'
    when ''
      'Fine. Be that way.'
    when nil
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end
end
