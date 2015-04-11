class Bob
  def hey(statement)
    if statement.empty?
      'Fine. Be that way.'
    elsif statement.upcase == statement
      'Woah, chill out!'
    elsif statement.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
