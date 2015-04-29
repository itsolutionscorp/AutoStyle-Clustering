class Bob

  def hey(input)
    case
    when input.strip.empty?    then 'Fine. Be that way!'
    when input == input.upcase then 'Woah, chill out!'
    when input.end_with?('?')  then 'Sure.'
    else 'Whatever.'
    end
  end

end
