class Bob

  def hey (statement)
    if statement.strip.empty?
      'Fine. Be that way!'
    elsif (statement.upcase == statement) && (statement =~/[A-Z]/)
      'Woah, chill out!'
    elsif statement.end_with? "?"
      'Sure.'
    else
      'Whatever.'
    end
  end
end
