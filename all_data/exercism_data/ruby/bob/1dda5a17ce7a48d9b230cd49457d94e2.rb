class Bob
  def hey name
    case
    when name === name.upcase && name =~ /[a-zA-Z]/
      'Woah, chill out!'
    when name.end_with?('?')
      'Sure.'
    when name.strip === ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
