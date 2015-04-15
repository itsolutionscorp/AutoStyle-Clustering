class Bob
  def hey(statement)
    if statement.strip.length == 0
      'Fine. Be that way!'
    elsif statement.upcase == statement && (statement =~ /[A-Z]+/)
      'Woah, chill out!'
    elsif statement.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
