class Bob
  def hey(statement)
    if statement.class != String
     'Whatever.'
    elsif statement.strip.empty?
     'Fine. Be that way!'
    elsif (statement.upcase == statement) && (statement =~ /[A-Z]/)
      'Woah, chill out!'
    elsif statement.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
