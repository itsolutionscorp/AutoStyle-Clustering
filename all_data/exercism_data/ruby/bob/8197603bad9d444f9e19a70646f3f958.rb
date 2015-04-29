class Bob
  def hey(msg)
    case msg
    when /\A\s*\Z/
      'Fine. Be that way!'
    when /\A[^a-z]+\Z/
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
