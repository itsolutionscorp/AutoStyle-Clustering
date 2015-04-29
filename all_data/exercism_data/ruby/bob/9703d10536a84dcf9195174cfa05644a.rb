class Bob
  def hey(text)
    case text
    when text.strip!, ''
      'Fine. Be that way!'
    when text.upcase
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
