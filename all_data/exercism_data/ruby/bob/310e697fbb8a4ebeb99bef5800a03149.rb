class Bob
  def hey(text)
    case text
    when /\A *\z/
      'Fine. Be that way!'
    when /(?=.*[A-Z])^[$^ 0-9A-Z\p{P}]*$/
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
