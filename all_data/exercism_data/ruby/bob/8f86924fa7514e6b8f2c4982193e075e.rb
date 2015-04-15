class Bob

  def hey greeting
    case greeting.strip
    when ''
      'Fine. Be that way!'
    when /^[^a-z]+$/
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
