class Bob
  def hey(input)
    case input
    when /\?$/
      'Sure.'
    when nil, ""
      'Fine. Be that way.'
    when /^[^a-z]+$/
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
