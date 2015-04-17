class Bob

  def hey(statement)
    if statement.strip.empty?
      'Fine. Be that way!'
    elsif statement =~ /[A-Z]/ && statement == statement.upcase
      "Woah, chill out!"
    elsif statement.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end