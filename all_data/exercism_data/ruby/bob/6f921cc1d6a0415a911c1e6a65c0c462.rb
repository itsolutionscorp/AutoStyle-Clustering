class Bob

  def hey(incoming=nil)
    case
    when incoming.strip.empty?
      'Fine. Be that way!'
    when incoming == incoming.upcase
      'Woah, chill out!'
    when incoming.strip.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end
