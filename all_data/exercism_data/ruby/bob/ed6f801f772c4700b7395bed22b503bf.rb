class Bob
  def hey(input)

    case
    when input.strip.to_s.empty?
      'Fine. Be that way!'
    when input == input.upcase
      'Woah, chill out!'
    when input.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
