class Bob
  def hey(input)
    case input
    when /\A[^a-z]*[A-Z]+[^a-z]*\Z/m
      'Whoa, chill out!'
    when /\?\Z/m
      'Sure.'
    when /\A[ \t]*\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
