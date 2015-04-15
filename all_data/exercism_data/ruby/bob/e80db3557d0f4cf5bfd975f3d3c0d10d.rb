class Bob
  def hey(input)
    case input
    when /\?\Z/
      'Sure.'
    when /\A[^a-z]+\Z/
      'Woah, chill out!'
    when nil, ""
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end
end
