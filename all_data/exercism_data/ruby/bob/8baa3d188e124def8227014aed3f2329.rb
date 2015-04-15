class Bob
  def hey(statement)
    respond statement
  end

  def respond(statement)
    if statement.class != String
    'Whatever.'
    elsif statement.strip.empty?
    'Fine. Be that way!'
    elsif statement.upcase == statement
    'Woah, chill out!'
    elsif statement.end_with?('?')
    'Sure.'
    else
    'Whatever.'
    end
  end
end
