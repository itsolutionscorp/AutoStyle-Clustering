class Bob
  def hey statement=""
    statement ||= ""
    
    if statement.strip.empty?
      'Fine. Be that way!'
    elsif statement.upcase == statement
      'Woah, chill out!'
    elsif statement[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
