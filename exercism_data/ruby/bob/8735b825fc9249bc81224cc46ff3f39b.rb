class Bob
  def hey(statement)
    statement.gsub!("\n", ' ')
    if statement.strip.empty?
      'Fine. Be that way!' 
    elsif statement =~ /[a-zA-Z]/ and statement == statement.upcase
     'Woah, chill out!'
    elsif statement =~ /^.+[?]$/
      'Sure.'
    else 
      'Whatever.'
    end
  end
end
