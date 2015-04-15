class Bob

  def hey(input)
    case
    when input.strip.empty?
      'Fine. Be that way!'
    when input.eql?(input.upcase)
      'Woah, chill out!'
    when input.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
end
