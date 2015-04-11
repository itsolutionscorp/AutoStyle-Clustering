class Bob
  attr_reader :bob

  def hey(input)
    case 
    when input == ''
      'Fine'
    when input == input.upcase  && input.end_with?('!')
      'Woah, chill out!'
    when input.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
