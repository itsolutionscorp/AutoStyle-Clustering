class Bob
  def hey(statement)
    statement.strip!
    if statement =~ /^[^a-z]+$/ and statement =~ /[A-Z]/
      'Woah, chill out!'
    elsif statement =~ /^.*\?\n.*[^?]$/
      'Whatever.'
    elsif statement =~ /.*\?$/
      'Sure.'
    elsif statement.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
