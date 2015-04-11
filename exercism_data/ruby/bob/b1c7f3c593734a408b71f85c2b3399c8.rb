class Bob
  def hey(input)
    case 
    when input == '' 
     'Fine. Be that way!'
    when input.chars.all? { |e|  e == ' '}
     'Fine. Be that way!'
    when input == input.upcase
     'Woah, chill out!'
    when input.include?("?") && !input.end_with?('?')
     'Whatever.'
    when input.lines.count > 1
     'Whatever.' 
    when input.end_with?('?') 
     'Sure.'
    else 'Whatever.'

    end
  end


end
