class Bob
  attr_reader :bob

  def hey(input)
    input = input.rstrip
    case 
    when input == '' 
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
