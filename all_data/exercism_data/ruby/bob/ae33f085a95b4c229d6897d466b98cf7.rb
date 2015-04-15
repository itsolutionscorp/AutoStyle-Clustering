class Bob
  attr_reader :bob

  def hey(input)
    case 
    when input.rstrip &&  input == '' 
      'Fine. Be that way!'
    when input == input.upcase || input.end_with?('!')
      'Woah, chill out!'
    when input.end_with?('?')
      'Sure.'
    when input('gym')
      'Whatever.'
    else
      'Whatever.'
    end
  end
end
