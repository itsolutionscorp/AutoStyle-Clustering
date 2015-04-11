class Bob

  def hey(input)
    input = input.strip
    case
    when input.empty?
      'Fine. Be that way!'
    when input.upcase == input
      'Woah, chill out!'
    when input.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end
