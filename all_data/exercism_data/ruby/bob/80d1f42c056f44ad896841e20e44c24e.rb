class Bob
  def hey(message)
    m = message.strip

    case
    when m == ''
      'Fine. Be that way!'
    when !m[/[a-z]/]
      'Woah, chill out!'
    when m.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
