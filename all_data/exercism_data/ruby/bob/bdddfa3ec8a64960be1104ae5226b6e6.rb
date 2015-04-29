class Bob
  def hey(statement)
    if statement.to_s.empty?
      'Fine. Be that way.'
    elsif statement.end_with?('?')
      'Sure.'
    elsif statement.upcase == statement
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
