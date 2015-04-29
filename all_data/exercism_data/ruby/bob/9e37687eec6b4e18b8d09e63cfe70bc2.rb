class Bob

  def hey(incoming=nil)
    response = ""
    if incoming.strip.empty?
      response = 'Fine. Be that way!'
    elsif incoming == incoming.upcase
      response = 'Woah, chill out!'
    elsif incoming.strip.end_with? '?'
      response = 'Sure.'
    else
      response = 'Whatever.'
    end
    response
  end

end
