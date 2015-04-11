class Bob
  def hey(statement)
    if statement.nil? || statement.empty?
      'Fine. Be that way.'
    elsif statement.upcase == statement
      'Woah, chill out!'
    elsif statement[-1, 1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
