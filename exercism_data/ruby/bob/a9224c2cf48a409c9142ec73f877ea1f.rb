class Bob
  def hey(str)
    case
    when str.strip.empty?
      'Fine. Be that way!'
    when str == str.upcase
      'Woah, chill out!'
    when str.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
